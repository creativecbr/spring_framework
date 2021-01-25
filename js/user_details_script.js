import {getBackendUrl} from './configuration.js';
import {clearElementChildren, createUserAdRow, createBaner, getParameterByName, setText} from '/js/dom_utils.js';


window.addEventListener('load', () => {

    fetchAndDisplayUser();

});

function fetchAndDisplayUser() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);

            for (const [key, value] of Object.entries(response)) {
                let field = document.getElementById(key);
                if (field) {
                    clearElementChildren(field);
                    field.appendChild(document.createTextNode(value));
                }
            }
        }
    };


    xhttp.open("GET", getBackendUrl() + '/users/' + getParameterByName('user'), true);
    xhttp.send();
}
