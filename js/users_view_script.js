import {getBackendUrl} from './configuration.js';
import {clearElementChildren, createUserRow, createBaner, createUserRowAndDisplay} from '/js/dom_utils.js';


window.addEventListener('load', () => {
    fetchAndDisplayUsers();
});


function fetchAndDisplayUsers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayUsers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/users', true);
    xhttp.send();
}

function displayUsers(users)
{
    let container = document.getElementById('users-list-content');
    clearElementChildren(container);

    users.users.forEach(user => {
        container.appendChild(createUserRowAndDisplay(container, user, () => deleteUser(user)));
    })

}

function deleteUser(user)
{
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayUsers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/users/' + user, true);
    xhttp.send();
}

