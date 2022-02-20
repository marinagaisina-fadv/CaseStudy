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

const generateUsername= () => {
    let email = document.querySelector("#article-register input[name='email']");
    let firstName = document.querySelector("#article-register input[name='firstName']");
    let lastName = document.querySelector("#article-register input[name='lastName']");
    let username = document.querySelector("#article-register input[name='username']");
    if ((email.value === '')||(firstName.value === '')||(lastName.value === '')) {
        return;
    }
    let usernameText = '';
    usernameText += "@"+ firstName.value.substring(0,2)+lastName.value.substring(0,3)+email.value.substring(0,3)+Math.floor((Math.random() * 1000) + 1);
    username.setAttribute('value', usernameText);
}