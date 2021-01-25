import {getBackendUrl} from './configuration.js';
import {clearElementChildren, createUserAdRow, createBaner, getParameterByName, setText} from '/js/dom_utils.js';


function submitEditAd() {
    if(document.forms['edit-ad-form'].title.value === "" ||
        document.forms['edit-ad-form'].description.value === "")
    {
        console.log("TODO");
    }
    else
    {
        editAd();
    }
}

window.addEventListener('load', () => {


    fetchAndDisplayAdd();

    let sear = document.getElementById("send-edited-ad-request");
    sear.addEventListener("click", () => { submitEditAd(); })


});

function fetchAndDisplayAdd() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);

            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };


    xhttp.open("GET", getBackendUrl() + '/users/' + getParameterByName('user') + '/ads/' + getParameterByName('ad'), true);
    xhttp.send();
}

function editAd()
{
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/users/' + getParameterByName('user') + '/ads/' + getParameterByName('ad') , true);

    const request = {
        'title': document.getElementById('title').value,
        'description': document.getElementById('description').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    location.href="user_view.html?user="+getParameterByName('user');
}