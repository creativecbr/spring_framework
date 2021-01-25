import {getBackendUrl} from './configuration.js';
import {clearElementChildren, createUserAdRow, createBaner, getParameterByName, setText} from '/js/dom_utils.js';


function submitUpdateUser() {
    if(document.forms['edit-user-form'].name.value === "" ||
        document.forms['edit-user-form'].surname.value === "" ||
        document.forms['edit-user-form'].birthDate.value === "" ||
        document.forms['edit-user-form'].password.value === ""  )
    {
        console.log("TODO");
    }
    else
    {
        updateUser();
    }
}

window.addEventListener('load', () => {

    let save_button = document.getElementById("send-edited-user-request");
    let delete_button = document.getElementById("delete-account-button");
    let add_ad_button = document.getElementById("add-ad-href");

    save_button.addEventListener("click", () => { submitUpdateUser(); })
    delete_button.addEventListener('click', () => { deleteUser(); })
    add_ad_button.href="../ad_add.html?user=" + getParameterByName('user');
    fetchAndDisplayUser();
    fetchAndDisplayUserAds();

});

function deleteUser()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            location.href="index.html";
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/users/' + getParameterByName('user'), true);
    xhttp.send();
}

function fetchAndDisplayUser() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            let user_link = document.getElementById("user-details");
            user_link.href="user_details.html?user=" + response['login'];
            user_link.alt=response['login'] + " informacje o profilu";
            setText("login", response['login']);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/users/' + getParameterByName('user'), true);
    xhttp.send();
}

function updateUser()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayUser();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/users/' + getParameterByName('user'), true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value,
        'birthDate': document.getElementById('birthDate').value,
        'password': document.getElementById('password').value
    };


    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.reload(true);

}
function fetchAndDisplayUserAds()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayUserAds(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/users/' + getParameterByName('user') + '/ads', true);
    xhttp.send();

}

function displayUserAds(response)
{
    let container = document.getElementById("main-left");
    clearElementChildren(container);
    container.appendChild(createBaner("Ogłoszenia użytkownika " + getParameterByName('user')));
    response.ads.forEach(ad => {
        container.appendChild(createUserAdRow(ad, getParameterByName('user')));
    })
}
