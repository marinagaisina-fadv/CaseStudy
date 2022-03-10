package com.marinagaisina.casestudy.service;

import com.marinagaisina.casestudy.database.dao.ItemDAO;
import com.marinagaisina.casestudy.database.dao.ParcelDAO;
import com.marinagaisina.casestudy.database.entities.Item;
import com.marinagaisina.casestudy.database.entities.Parcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


//@Service
public class ItemService {

    public static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ParcelDAO parcelDAO;

    public boolean addItemToPackage(Item item, Parcel parcel) {
        return addItemToPackage(item.getId(), item.getQuantityToPackage(), parcel.getId());
    }

    public boolean addItemToPackage(Integer itemId, Integer quantityToPackage, Integer parcelId) {
        Parcel parcelDB = null;
        Item itemDB = null;
        try {
            parcelDB = parcelDAO.findById(parcelId);
            itemDB = itemDAO.findById(itemId);
        } catch (Exception e) {
            LOG.warn(String.valueOf(e.getCause()));
        }
        if ((parcelDB == null) || (itemDB == null)) {
            LOG.warn("No parcel or no item were found in DB.");
            return false;
        }
        if (checkItemAvailability(itemDB, quantityToPackage)) {
            reduceItemAvailability(itemDB, quantityToPackage);
            itemDB.setQuantityToPackage(quantityToPackage);
            itemDAO.save(itemDB);

            parcelDB.getItems().add(itemDB);
            parcelDAO.save(parcelDB);
            return true;

        } else {
            LOG.warn("The remained quantity of items is less than it is requested. The item wasn't added to the package.");
            return false;
        }
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
            System.out.println("There are no \'"+itemDB.getName()+ "\' left in the system. The remained quantity in the storage is 0. Please make a request to the supplier.");
        }
        if (itemDB.getAvailQuantity() < 5) {
            System.out.println("We are running out of \'"+itemDB.getName()+ "\'. The remained quantity in the storage is "+
                    itemDB.getAvailQuantity()+". Please make a request to the supplier.");
        }
    }
}
