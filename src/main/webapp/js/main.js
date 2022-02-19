'use strict';
// Open/Close articles by the link toggle (add/remove bootstrap visibility classes)

const formOpenClose = (param) => {
    const article = document.getElementById(param);
    if (article.classList.contains('d-none')) {
        article.classList.remove('d-none');
        article.classList.add("d-block");
    } else {
        article.classList.remove('d-block');
        article.classList.add('d-none');
    }
}