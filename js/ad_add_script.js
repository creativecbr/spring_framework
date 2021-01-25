import {getBackendUrl} from './configuration.js';
import {clearElementChildren, createUserAdRow, createBaner, getParameterByName, setText} from '/js/dom_utils.js';


function getAllCategories() {

    let select_categories = document.getElementById("categories");
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [id, struct] of Object.entries(response.categories)) {
                let option = document.createElement("option");
                option.value = struct.name;
                option.appendChild(document.createTextNode(struct.name));
                select_categories.appendChild(option);
            }
        }
    };

    xhttp.open("GET", getBackendUrl() + '/categories', true);
    xhttp.send();
}

function submitAddAd() {

    if(document.forms['ad-add-form'].title.value === "" ||
        document.forms['ad-add-form'].description.value === "" ||
        document.forms['ad-add-form'].categories.value === "")
    {
        console.log("TODO");
    }
    else
    {
        addAd();
    }

}



window.addEventListener('load', () => {


    getAllCategories();
    let snar = document.getElementById("send-new-ad-request");
    snar.addEventListener("click", () => { submitAddAd(); })
    let file_upload = document.getElementById("icon");


    file_upload.onchange = function(event) {
        var file = file_upload.files;
        var btn = document.getElementById("btn-file-uploader");
        clearElementChildren(btn);
        btn.appendChild(document.createTextNode(file[0].name));
    }


});

function getCurrentDate() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = mm + '_' + dd + '_' + yyyy;
    return today;
}

function addAd()
{

    //information
    const xhttp = new XMLHttpRequest();
    var file_name = getParameterByName('user') + "_" + Math.floor(Math.random()*(10000000)) + ".jpg";
    xhttp.open("POST", getBackendUrl() + '/users/'+ getParameterByName('user') +'/ads' , true);

    const request = {
        'title': document.forms['ad-add-form'].title.value,
        'category': document.forms['ad-add-form'].categories.value,
        'description': document.forms['ad-add-form'].description.value,
        'iconPath': file_name
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));

    // icon
    uploadFile(file_name)

    location.href="user_view.html?user="+getParameterByName('user');
}


function uploadFile(file_name)
{
    const xhttp_icon = new XMLHttpRequest();

    xhttp_icon.open("POST", getBackendUrl() + '/files' , true);

    let request_icon = new FormData();
    request_icon.append("icon", document.getElementById("icon").files[0]);
    request_icon.append("filename", file_name);
    //xhttp_icon.setRequestHeader('Content-Type', 'multipart/form-data')

    xhttp_icon.send(request_icon);

}