let curr_user;
let change_table_btn;
let owners;
let keepers;
let weak = false;

window.onload = function(){
    console.log('admin page loaded');

    curr_user = get_logged_in_user();

    if(curr_user === undefined){
        alert('you are not logged in');
        window.open('visitor.html', '_self');
        return;
    }

    document.getElementById('exit-info-btn').style.display = 'none';
    document.getElementById('exit-info-btn').addEventListener('click', exit_info);
    document.getElementById('user-info').style.display = 'none';
    document.getElementById('keeper-info').style.display = 'none';
    document.getElementById('owner-info').style.display = 'none';

    document.getElementById('change-pass-btn').addEventListener('click', change_pass);
    document.getElementById('change-pass-form').style.display = 'none';

    document.getElementById('change-pass-form').addEventListener('submit', pass_submit);
    document.getElementById('cancel-btn').addEventListener('click', cancel_submit);

    document.getElementById('admin-log-out-btn').addEventListener('click', logout);
    change_table_btn = document.getElementById('change-table-btn');
    change_table_btn.innerHTML = "Show Pet Keepers";
    change_table_btn.addEventListener('click', change_table);

    owners = get_pet_owners();
    keepers = get_pet_keepers();


    create_owners_table();


}

function cancel_submit(){
    document.getElementById('user-table').style.display = 'block';
    document.getElementById('no-users-p').style.display = 'block';
    document.getElementById('change-table-btn').style.display = 'block';
    document.getElementById('user-info').style.display = 'none';
    document.getElementById('exit-info-btn').style.display = 'none';

    document.getElementById('change-pass-form').style.display = 'none';
}

function pass_submit(e){
    e.preventDefault();
    if (!e.submitter || e.submitter.id !== 'ok-btn') {
        console.log('other')
        return false;
    }

    if (!check_password())
        return false;

    if (weak === true)
        return false;

    document.getElementById('change-pass-form');

    let data = new FormData(document.getElementById('change-pass-form'));
    let form_object = {};
    data.forEach(function(value, key){
        form_object[key] = value;
    });
    console.log(form_object);
    let params = JSON.stringify(form_object);

    const xhr = new XMLHttpRequest();
    xhr.onload = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                document.getElementById('change-pass-form').reset();
                alert('Password changed!');
                cancel_submit();
                document.getElementById('change-pass-form').display = 'none';
            }else if(xhr.status === 500){
                alert('exception');
            }else{
                alert('unknown status');
            }
        }
    }

    xhr.open('POST', 'ChangePassword');
    xhr.send(params);

    return false;
}



function strength() {
    weak = false;
    let pass = document.getElementById("password-input").value;
    let cnt = 0,
        num = 0,
        sp = 0,
        up = 0,
        low = 0;
    let flag = true;
    let strong = false;
    document.getElementById("message2").innerHTML = "";
    document.getElementById("message3").innerHTML = "";
    document.getElementById("message4").innerHTML = "";
    document.getElementById("message5").innerHTML = "";
    if (pass.includes("cat")) {
        //checks if some forbidden words are included in the password
        document.getElementById("message2").innerHTML =
            'You can\'t use the sequence "cat".';
        flag = false;
    }
    if (pass.includes("dog")) {
        document.getElementById("message2").innerHTML =
            'You can\'t use the sequence "dog".';
        flag = false;
    }
    if (pass.includes("gata")) {
        document.getElementById("message2").innerHTML =
            'You can\'t use the sequence "gata".';
        flag = false;
    }
    if (pass.includes("skylos")) {
        document.getElementById("message2").innerHTML =
            'You can\'t use the sequence "skylos".';
        flag = false;
    }
    function containsSpecialChars(str) {
        //checks if the password cointains any special characters
        const specialChars = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
        return specialChars.test(str);
    }
    for (let i = 0; i < pass.length; i++) {
        //counts the all the elements and also the digits of the password
        cnt++;
        if (!isNaN(pass.charAt(i))) {
            num++;
        }
        if (containsSpecialChars(pass.charAt(i))) {
            //counts the special characters of the password
            sp++;
        } else if (
            isNaN(pass.charAt(i)) &&
            pass.charAt(i).toUpperCase() === pass.charAt(i)
        ) {
            //checks if the password cointains any uppercase
            up++;
        } else if (
            isNaN(pass.charAt(i)) &&
            pass.charAt(i).toLowerCase() === pass.charAt(i)
        ) {
            //checks if the password cointains any lowercase
            low++;
        }
    }
    if(sp>0 && num>0){
        strong=true;
    }
    if (num >= Math.ceil(cnt / 2)) {
        //checks if the password is weak
        document.getElementById("message3").innerHTML = "Weak password!";
        flag = false;
        weak = true;
    } else if (strong === true && up >= 1 && low >= 1) {
        //checks if the password is strong
        document.getElementById("message4").innerHTML = "Strong password!";
    } else {
        document.getElementById("message5").innerHTML = "Medium password!"; //checks if the password is medium
    }
    return flag;
}


function check_password(e) {
    //password checks
    let pass = document.getElementById("password-input").value;
    let conf = document.getElementById("confirm-pass-input").value;
    document.getElementById("message1").innerHTML = "";
    if (pass !== conf) {
        //checks if 2 passwords are the same
        document.getElementById("message1").innerHTML =
            "Passwords are not the same.";
        console.log("false in check")
        return false
    }
    console.log("true in check")
    return true;
}

function show_hide_pass() {
    //show/hide password for password field
    if (document.getElementById("password-input").type === "text") {
        document.getElementById("password-input").type = "password";
        document.getElementById("show-hide-pass-btn").value = "Show Password";
    } else {
        document.getElementById("password-input").type = "text";
        document.getElementById("show-hide-pass-btn").value = "Hide Password";
    }
}


function change_pass(){
    document.getElementById('user-table').style.display = 'none';
    document.getElementById('no-users-p').style.display = 'none';
    document.getElementById('change-table-btn').style.display = 'none';
    document.getElementById('user-info').style.display = 'none';
    document.getElementById('exit-info-btn').style.display = 'none';

    document.getElementById('change-pass-form').style.display = 'block';
    document.getElementById('change-pass-form').reset();

    document.getElementById('show-hide-pass-btn').addEventListener('click', show_hide_pass);
    document.getElementById("show-hide-confirm-pass-btn").addEventListener('click', show_hide_conf_pass);

    document.getElementById("password-input").addEventListener('keyup', check_password);
    document.getElementById("confirm-pass-input").addEventListener('keyup', check_password);

}

function show_hide_conf_pass() {
    //show/hide password for confirm field
    if (document.getElementById("confirm-pass-input").type === "text") {
        document.getElementById("confirm-pass-input").type = "password";
        document.getElementById("show-hide-confirm-pass-btn").value = "Show Password";
    } else {
        document.getElementById("confirm-pass-input").type = "text";
        document.getElementById("show-hide-confirm-pass-btn").value = "Hide Password";
    }
}


function create_owners_table(){

    let table = document.getElementById("user-table").getElementsByTagName('tbody')[0];

    if(owners.length === 0){
        document.getElementById('user-table').style.display = 'none';
        document.getElementById('no-users-p').style.display = 'block';
    }else {
        document.getElementById('user-table').style.display = 'block';
        document.getElementById('no-users-p').style.display = 'none';
    }

    for(let i=0; i<owners.length; i++){
        let newRow = table.insertRow();
        newRow.id = i + '-or';
        let cell1 = newRow.insertCell(0);
        let cell2 = newRow.insertCell(1);

        cell1.innerHTML = owners[i].username;
        cell2.innerHTML = '<button id=\"' + i + '-v\" onclick="view(this)">View</button> <br> <button id=\"' + i + '\-d" onclick="del(this)">Delete</button>';
    }


}


function create_keepers_table(){

    let table = document.getElementById("user-table").getElementsByTagName('tbody')[0];

    if(keepers.length === 0){
        document.getElementById('user-table').style.display = 'none';
        document.getElementById('no-users-p').style.display = 'block';
    }else {
        document.getElementById('user-table').style.display = 'block';
        document.getElementById('no-users-p').style.display = 'none';
    }

    for(let i=0; i<keepers.length; i++){
        let newRow = table.insertRow();
        newRow.id = i + '-kr';
        let cell1 = newRow.insertCell(0);
        let cell2 = newRow.insertCell(1);

        cell1.innerHTML = keepers[i].username;
        cell2.innerHTML = '<button id=\"' + i + '-v\" onclick="view(this)">View</button> <br> <button id=\"' + i + '\-d" onclick="del(this)">Delete</button>';
    }

}



function delete_owner_rows(){
    const allRows = document.querySelectorAll('tr');

    allRows.forEach(row => {
        if (/^\d+-or$/.test(row.id)) {
            row.remove();
        }
    });
}

function delete_keeper_rows(){
    const allRows = document.querySelectorAll('tr');

    allRows.forEach(row => {
        if (/^\d+-kr$/.test(row.id)) {
            row.remove();
        }
    });
}

function refresh_owner_table(){
    delete_owner_rows();
    create_owners_table();
}


function refresh_keeper_table(){
    delete_keeper_rows();
    create_keepers_table();
}


function delete_user(username, u_type, index){
    let params;
    const xhr = new XMLHttpRequest();


    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                if(u_type === 'PET_OWNER'){
                    owners.splice(index, 1);
                    refresh_owner_table();
                }else{
                    keepers.splice(index, 1);
                    refresh_keeper_table();
                }
            }else if(xhr.status === 500){
                alert('exception');
            }else{
                alert('unknown code');
            }
        }
    }


    params = '{"username":"' + username + '", "user_type":"' + u_type + '"}';
    xhr.open('POST', 'DeleteUser');
    xhr.send(params);
}


function view_user(username, u_type, index){
    let params;
    const xhr = new XMLHttpRequest();


    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                show_user_info(JSON.parse(xhr.responseText));
            }else if(xhr.status === 500){
                alert('exception');
            }else{
                alert('unknown code');
            }
        }
    }


    params = '{"username":"' + username + '", "user_type":"' + u_type + '"}';
    xhr.open('POST', 'GetUser');
    xhr.send(params);

}

function exit_info(){
    document.getElementById('user-table').style.display = 'block';
    document.getElementById('change-table-btn').style.display = 'block';
    document.getElementById('exit-info-btn').style.display = 'none';
    document.getElementById('user-info').style.display = 'none';
    document.getElementById('owner-info').style.display = 'none';
    document.getElementById('keeper-info').style.display = 'none';
}

function show_user_info(info){
    document.getElementById('user-table').style.display = 'none';
    document.getElementById('no-users-p').style.display = 'none';
    document.getElementById('change-table-btn').style.display = 'none';
    document.getElementById('exit-info-btn').style.display = 'block';
    document.getElementById('user-info').style.display = 'block';
    document.getElementById('owner-info').style.display = 'block';

    document.getElementById('username-span').innerHTML = info.username;
    document.getElementById('first-name-span').innerHTML = info.firstname;
    document.getElementById('last-name-span').innerHTML = info.lastname;
    document.getElementById('birthdate-span').innerHTML = info.birthdate;

    if(info.gender === 'MALE'){
        document.getElementById('gender-span').innerHTML = 'Male';
    }else if(info.gender === 'FEMALE'){
        document.getElementById('gender-span').innerHTML = 'Female';
    }else{
        document.getElementById('gender-span').innerHTML = 'Other';
    }

    document.getElementById('country-span').innerHTML = info.country;
    document.getElementById('city-span').innerHTML = info.city;
    document.getElementById('address-span').innerHTML = info.address;
    document.getElementById('personal-page-span').innerHTML = info.personalpage;
    document.getElementById('job-span').innerHTML = info.job;
    document.getElementById('telephone-span').innerHTML = info.telephone;

    if(info.user_type === 'PET_KEEPER'){
        document.getElementById('keeper-info').style.display = 'block';

        if(info.property === 'EXTERIOR'){
            document.getElementById('property-span').innerHTML = 'Exterior';
        }else if(info.property === 'INTERIOR'){
            document.getElementById('property-span').innerHTML = 'Interior';
        }else{
            document.getElementById('property-span').innerHTML = 'Both';
        }


        if(info.hosting === 'CAT_KEEPING'){
            document.getElementById('hosting-span').innerHTML = 'Cat Keeper';
            document.getElementById('dogprice-p').style.display = 'none';
            document.getElementById('catprice-p').style.display = 'block';
            document.getElementById('catprice-span').innerHTML = info.catprice;
        }else if(info.hosting === 'DOG_KEEPING'){
            document.getElementById('hosting-span').innerHTML = 'Dog Keeper';
            document.getElementById('dogprice-p').style.display = 'block';
            document.getElementById('catprice-p').style.display = 'none';
            document.getElementById('dogprice-span').innerHTML = info.dogprice;
        }else{
            document.getElementById('hosting-span').innerHTML = 'Both';
            document.getElementById('dogprice-p').style.display = 'block';
            document.getElementById('catprice-p').style.display = 'block';
            document.getElementById('dogprice-span').innerHTML = info.dogprice;
            document.getElementById('catprice-span').innerHTML = info.catprice;
        }

        document.getElementById('description-span').innerHTML = info.propertydescription;
    }


}

function view(btn){
    let index = btn.id.split('-')[0];
    let username;
    let u_type;

    if(change_table_btn.innerHTML.includes('Keepers')){
        username = owners[index].username;
        u_type = 'PET_OWNER';
    }else{
        username = keepers[index].username;
        u_type = 'PET_KEEPER';
    }

    console.log('view ' + username);
    view_user(username, u_type);
}

function del(btn){
    let index = btn.id.split('-')[0];
    let u_type;
    let username;

    if(change_table_btn.innerHTML.includes('Keepers')){
        username = owners[index].username;
        u_type = 'PET_OWNER';
    }else{
        username = keepers[index].username;
        u_type = 'PET_KEEPER';
    }

    delete_user(username, u_type, index);
}

function get_pet_owners(){
    const xhr = new XMLHttpRequest();
    let tmp;
    xhr.onload = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                tmp = JSON.parse(xhr.responseText);
            }else if(xhr.status === 500) {
                alert('exception');
            }else {
                alert('unknown code');
            }
        }
    }

    xhr.open('GET', 'GetOwners', false);
    xhr.send();
    return tmp;
}



function get_pet_keepers(){
    const xhr = new XMLHttpRequest();
    let tmp;
    xhr.onload = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                tmp = JSON.parse(xhr.responseText);
            }else if(xhr.status === 500) {
                alert('exception');
            }else {
                alert('unknown code');
            }
        }
    }

    xhr.open('GET', 'GetKeepers', false);
    xhr.send();
    return tmp;
}

function change_table(){
    if(change_table_btn.innerHTML === 'Show Pet Keepers'){
        change_table_btn.innerHTML = 'Show Pet Owners';
        delete_owner_rows();
        create_keepers_table();
    }else{
        change_table_btn.innerHTML = 'Show Pet Keepers';
        delete_keeper_rows();
        create_owners_table();
    }
}


function logout(){
    const xhr = new XMLHttpRequest();
    console.log("log out")
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                curr_user = undefined;
                window.open('index.html', '_self');
            }else if(xhr.status === 500){
                alert("Exception");
            }else{
                alert("unknown status");
            }
        }
    }

    xhr.open('GET', 'LogOut');
    xhr.send();
}

function get_logged_in_user(){
    const xhr = new XMLHttpRequest();
    let tmp_data;
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                tmp_data = JSON.parse(xhr.responseText);
            }else if(xhr.status === 400){
                curr_user = undefined;
            }else{
                alert('unknown code');
            }
        }
    }

    xhr.open('GET', 'GetLoggedInUser', false);

    xhr.send();
    return tmp_data;
}


