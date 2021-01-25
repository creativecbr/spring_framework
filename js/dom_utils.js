import {getBackendUrl} from "./configuration.js";

export function createUserRowAndDisplay(container, user, action) {


        let user_row = document.createElement("div");

        user_row.className="user-row shadow";
        container.appendChild(user_row);

        let user_row_left = document.createElement("div");
        user_row_left.className="user-row-left";
        let user_row_img = document.createElement("div");
        user_row_img.className = "user-row-img";
        let user_img = document.createElement("img");
        user_img.className="shadow";
        user_img.src="https://www.learning.uclg.org/sites/default/files/styles/featured_home_left/public/no-user-image-square.jpg?itok=PANMBJF-";
        user_img.alt="user";
        user_img.style.height="60px";
        user_img.style.width="auto";
        user_row_img.appendChild(user_img);
        user_row_left.appendChild(user_row_img);


        let user_row_login = document.createElement("div");
        user_row_login.className = "user-row-login";
        user_row_login.textContent = user;
        user_row_left.appendChild(user_row_login);

        user_row.appendChild(user_row_left);

        let user_row_right = document.createElement("div");
        user_row_right.className = "user-row-right";
        let user_row_button_edit = document.createElement("div");
        user_row_button_edit.className="user-row-button-edit";
        let user_row_button_delete = document.createElement("div");
        user_row_button_delete.className="user-row-button-delete";


        let edit_button = document.createElement("div");
        let a_edit_button = document.createElement("a");
        a_edit_button.href='../../user_view.html?user=' + user;
        a_edit_button.appendChild(edit_button);
        edit_button.className=" btn btn-user btn-primary btn-ghost";
        edit_button.appendChild(document.createTextNode("Edytuj"))
        user_row_button_edit.appendChild(a_edit_button);
        user_row_right.appendChild(user_row_button_edit);

        let delete_button = document.createElement("div");
        delete_button.className=" btn btn-user btn-primary btn-ghost";
        delete_button.appendChild(document.createTextNode("Usuń"));
        delete_button.addEventListener('click', action);
        user_row_button_delete.appendChild(delete_button);
        user_row_right.appendChild(user_row_button_delete);
        user_row.appendChild(user_row_right);
        return user_row;

}

export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}

export function setText(id, text)
{
    let e = document.getElementById(id);
    clearElementChildren(e);
    e.appendChild(document.createTextNode(text));
}

export function createBaner(text)
{
    let baner = document.createElement('div');
    baner.className="baner baner-long";
    let span = document.createElement('span');
    baner.appendChild(span);
    span.appendChild(document.createTextNode(text));
    span.style.fontWeight="700";
    return baner;

}

export function createUserRow(user, action)
{
    let post = document.createElement('div');
    post.className="post";

    let post_icon = document.createElement('div');
    post_icon.className="post-icon";
    let div = document.createElement('div');
    let img_no_user = document.createElement('img');

    img_no_user.alt="no user";
    img_no_user.src="https://tanzolymp.com/images/default-non-user-no-photo-1.jpg";
    img_no_user.style.height="50px";
    img_no_user.style.width="auto";
    div.appendChild(img_no_user);
    post_icon.appendChild(div);

    div = document.createElement('div');
    let span = document.createElement('span');
    span.style.fontSize="12px";
    span.style.top="-5px";
    span.style.position="relative";
    span.appendChild(document.createTextNode("Ranga"));
    div.appendChild(span);
    post_icon.appendChild(div);

    let post_content = document.createElement('div');
    post_content.className="post-content";

    let users_content_left = document.createElement('div');
    users_content_left.className="users-content-left";
    let span_login = document.createElement('span');
    span_login.className="user-login";
    span_login.appendChild(document.createTextNode(user));
    users_content_left.appendChild(span_login);


    let users_content_right = document.createElement('div');
    users_content_right.className="users-content-right";

    let profile_left_button = document.createElement('div');
    profile_left_button.className="profile-left-button inline-half-div";

    let button_edit = document.createElement('a');
    button_edit.className="button";
    button_edit.appendChild(document.createTextNode("Edytuj"));
    button_edit.href='../../user_view.html?user=' + user;


    let profile_right_button = document.createElement('div');
    profile_right_button.className="profile-right-button inline-half-div";

    let button_delete = document.createElement('div');
    button_delete.className="button";
    button_delete.appendChild(document.createTextNode("Usuń"));
    button_delete.addEventListener('click', action);

    let clear_both = document.createElement('div');
    clear_both.className="clear-both";

    profile_left_button.appendChild(button_edit);
    profile_right_button.appendChild(button_delete);

    users_content_right.appendChild(profile_left_button);
    users_content_right.appendChild(profile_right_button);
    users_content_right.appendChild(clear_both);


    post_content.appendChild(users_content_left);
    post_content.appendChild(users_content_right);

    post.appendChild(post_icon);
    post.appendChild(post_content);

    return post;
}


export function createUserAdRow(ad, user)
{
    console.log(ad);
    var iconPath = ad['iconPath'];
    if(iconPath == "" || iconPath == null || iconPath === "")
    {
        iconPath = "unknown_ad.jpg";
    }

    let post = document.createElement('div');
    post.className="post";
    let link_to_details = document.createElement('a');
    link_to_details.href="ad_details.html?user=" + user + "&ad=" + ad['id'];

    let post_icon = document.createElement('div');
    let post_content = document.createElement('div');
    let div_top = document.createElement('div');
    div_top.style.width="80%";
    div_top.style.float="left";
    div_top.appendChild(post_icon);
    link_to_details.appendChild(post_content);
    div_top.appendChild(link_to_details);

    let div_bottom = document.createElement('div');
    div_bottom.style.width="20%";
    div_bottom.style.textAlign="center";
    div_bottom.style.marginTop="2%";
    div_bottom.style.align="center";
    div_bottom.style.float="left";

    let edit_add_button = document.createElement('a');
    edit_add_button.className="button";
    edit_add_button.id="submit-button";
    edit_add_button.href="edit_ad.html?user=" + user + "&ad=" + ad['id'];
    edit_add_button.appendChild(document.createTextNode("Edytuj"));

    div_bottom.appendChild(edit_add_button);
    post.appendChild(div_top);
    post.appendChild(div_bottom);
    post_icon.className = "post-icon";
    post_content.className="post-content";
    let div = document.createElement('div');
    let ad_img = document.createElement('img');
    ad_img.src = getBackendUrl() + '/files/' + iconPath;
    ad_img.alt = "post icon";
    ad_img.style.height="50px";
    ad_img.style.width="auto";
    let download_img_link = document.createElement("a");
    download_img_link.href = getBackendUrl() + '/files/download/' + iconPath;
    download_img_link.alt = "Pobierz zdjęcie";
    download_img_link.appendChild(ad_img);
    download_img_link.setAttribute('download', iconPath);
    div.appendChild(download_img_link);
    post_icon.appendChild(div);

    div = document.createElement('div');
    let span = document.createElement('span');
    span.style.fontSize="12px";
    span.style.top="-5px";
    span.style.position="relative";
    span.appendChild(document.createTextNode(ad['category']));
    div.appendChild(span);
    post_icon.appendChild(div);

    span = document.createElement('span');
    span.style.fontWeight="bold";
    span.appendChild(document.createTextNode(ad['title']));

    post_content.appendChild(span);
    post_content.appendChild(document.createElement('br'));

    span = document.createElement('span');
    span.style.lineheight="2em";
    span.style.fontSize="14px";
    span.appendChild(document.createTextNode(ad['description']));
    post_content.appendChild(span);



    return post;

}
