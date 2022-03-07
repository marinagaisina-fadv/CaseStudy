package com.marinagaisina.casestudy.service;

import com.marinagaisina.casestudy.database.dao.ItemDAO;
import com.marinagaisina.casestudy.database.dao.ParcelDAO;
import com.marinagaisina.casestudy.database.entities.Item;
import com.marinagaisina.casestudy.database.entities.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class ItemService {

   @Autowired
   private static ItemService itemService;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ParcelDAO parcelDAO;

    public boolean addItemToPackage(Item item, Integer quantityToPackage, Parcel parcel) {
        Parcel parcelDB = null;
        Item itemDB = null;
        try {
            parcelDB = parcelDAO.findById(parcel.getId());
            itemDB = itemDAO.findById(item.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((parcelDB == null) || (itemDB == null)) {
            System.out.println("No parcel or no item were found in DB.");
            return false;
        } else {
            if (itemService.checkItemAvailability(item, quantityToPackage)) {
                itemService.reduceItemAvailability(item, quantityToPackage);
                itemDAO.save(item);
                parcelDB.getItems().add(itemDB);
                parcelDAO.save(parcelDB);
                return true;
            } else {
                System.out.println("The remained quantity of items is less than it is requested. The item wasn't added to the package.");
                return false;
            }
        }
    }

    public boolean addItemToPackage(Integer itemId, Integer quantityToPackage, Integer parcelId) {
        Parcel parcelDB = null;
        Item itemDB = null;
        try {
            parcelDB = parcelDAO.findById(parcelId);
            itemDB = itemDAO.findById(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((parcelDB == null) || (itemDB == null)) {
            System.out.println("No parcel or no item were found in DB.");
            return false;
        }
        return itemService.addItemToPackage(itemDB, quantityToPackage, parcelDB);
    }

    public boolean checkItemAvailability(Item item, Integer quantityToPackage) {
        Item itemDB = itemDAO.findById(item.getId());
        return quantityToPackage <= itemDB.getAvailQuantity();
    }

    public void reduceItemAvailability(Item item, Integer quantityToPackage) {
        Item itemDB = itemDAO.findById(item.getId());
        itemDB.setAvailQuantity(itemDB.getAvailQuantity() - quantityToPackage);
        itemDAO.save(itemDB);
        if (itemDB.getAvailQuantity() == 0) {
            System.out.println("There are no \'"+itemDB.getName()+ "\' left in the system. The remained quantity in the storage is 0.");
        }
        if (itemDB.getAvailQuantity() < 5) {
            System.out.println("We are running out of \'"+itemDB.getName()+ "\'. Please make a request to the supplier.");
        }
    }
}
