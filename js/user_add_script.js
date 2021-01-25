import {getBackendUrl} from './configuration.js';
import {clearElementChildren, createUserAdRow, createBaner, getParameterByName, setText} from '/js/dom_utils.js';


function submitAddUser() {
    if(document.forms['user-add'].name.value === "" ||
        document.forms['user-add'].surname.value === "" ||
        document.forms['user-add'].login.value === "" ||
        document.forms['user-add'].birthDate.value === "" ||
        document.forms['user-add'].password.value === "" ||
        document.forms['user-add'].email.value === "" )
    {
        console.log("TODO");
    }
    else
    {
        addUser();
    }



}

window.addEventListener('load', () => {

    if(getParameterByName('login')!=null)
    {
        location.href="index.html";
    }

    let snur = document.getElementById("send-new-user-request");
    snur.addEventListener("click", () => { submitAddUser(); })


});

function addUser()
{


    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/users' , true);

    const request = {
        'name': document.getElementById('name').value,
        'login': document.getElementById('login').value,
        'surname': document.getElementById('surname').value,
        'birthDate': document.getElementById('birthDate').value,
        'password': document.getElementById('password').value,
        'email': document.getElementById('email').value

    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));


}