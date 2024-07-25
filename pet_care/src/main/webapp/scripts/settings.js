let curr_user;
let data;
let tmp_changes;   // CONTAIS THE CHANGES SO THE curr_user will be untached IN ORDER TO CANCEL
let btn_map = new Map();
let form;
let change_pass_form;

let cat_keeping;
let dog_keeping;

let weak = false;

let f_name_btn;
let l_name_btn;
let birth_year_btn;
let gender_btn;
let property_btn;
let hosting_btn;
let dogprice_btn;
let catprice_btn;
let description_btn;
let country_btn;
let city_btn;
let address_btn;
let telephone_btn;
let personal_page_btn;
let job_btn;

window.onload = function (){
    console.log('settings page loaded');

    cat_keeping = false;
    dog_keeping = false;

    //document.getElementById('settings-form').style.display = 'none';
    document.getElementById('change-pass-form').style.display = 'none';

    document.getElementById('change-pass-btn').addEventListener('click', change_pass);

    form = document.getElementById('settings-form');
    form.addEventListener('submit', done_changes);

    document.getElementById('cancel-btn').addEventListener('click', cancel_pass);

    change_pass_form = document.getElementById('change-pass-form');
    change_pass_form.addEventListener('submit', submit_password);

    curr_user = get_userdata_settings();
    tmp_changes = structuredClone(curr_user);

    document.getElementById("pet_keeper_property").addEventListener('change', manage_property);
    document.getElementById('dog-keeper-ck-box').addEventListener('click', show_hide_dogpr);
    document.getElementById('cat-keeper-ck-box').addEventListener('click', show_hide_catpr);

    f_name_btn = document.getElementById('change-f-name-btn');
    l_name_btn = document.getElementById('change-l-name-btn');
    birth_year_btn = document.getElementById('change-birth-year-btn');
    gender_btn = document.getElementById('change-gender-btn');
    property_btn = document.getElementById('change-property-btn');
    hosting_btn = document.getElementById('change-keeping-btn');
    dogprice_btn = document.getElementById('change-dog-price-btn');
    catprice_btn = document.getElementById('change-cat-price-btn');
    description_btn = document.getElementById('change-prop-descr-btn');
    country_btn = document.getElementById('change-country-btn');
    city_btn = document.getElementById('change-city-btn');
    address_btn = document.getElementById('change-address-btn');
    telephone_btn = document.getElementById('change-phone-btn');
    personal_page_btn = document.getElementById('change-page-btn');
    job_btn = document.getElementById('change-job-btn');

    f_name_btn.addEventListener('click', change_fname);
    l_name_btn.addEventListener('click', change_lname);
    birth_year_btn.addEventListener('click', change_byear);
    gender_btn.addEventListener('click', change_gender);
    property_btn.addEventListener('click', change_property);
    hosting_btn.addEventListener('click', change_hosting);
    dogprice_btn.addEventListener('click', change_dogprice);
    catprice_btn.addEventListener('click', change_catprice);
    description_btn.addEventListener('click', change_description);
    country_btn.addEventListener('click', change_country);
    city_btn.addEventListener('click', change_city);
    address_btn.addEventListener('click', change_address);
    telephone_btn.addEventListener('click', change_telephone);
    personal_page_btn.addEventListener('click', change_personal_page);
    job_btn.addEventListener('click', change_job);

    btn_map.set(f_name_btn, false);
    btn_map.set(l_name_btn, false);
    btn_map.set(birth_year_btn, false);
    btn_map.set(gender_btn, false);
    btn_map.set(property_btn, false);
    btn_map.set(hosting_btn, false);
    btn_map.set(dogprice_btn, false);
    btn_map.set(catprice_btn, false);
    btn_map.set(description_btn, false);
    btn_map.set(country_btn, false);
    btn_map.set(city_btn, false);
    btn_map.set(address_btn, false);
    btn_map.set(telephone_btn, false);
    btn_map.set(personal_page_btn, false);
    btn_map.set(job_btn, false);

    hide_or_show_keepers_options();
    disable_inputs();
    fill_fields_with_info();
}

function submit_password(e){
    e.preventDefault();

    if (!e.submitter || e.submitter.id !== 'ok-btn')
        return false;

    if (!check_password())
        return false;

    if (weak === true)
        return false;


    let data = new FormData(change_pass_form);
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
                change_pass_form.reset();
                alert('Password changed!');
                form.style.display = 'block';
                change_pass_form.style.display = 'none';
            }else if(xhr.status === 500){
                alert('exception');
            }else{
                alert('unknown status');
            }
        }
    }

    xhr.open('POST', 'ChangePassword');
    xhr.send(params);

}

function cancel_pass(){
    form.style.display = 'block';
    change_pass_form.style.display = 'none'
}

function change_pass(){
    form.style.display = 'none';
    change_pass_form.reset();
    change_pass_form.style.display = 'block';

    show_hide_pass_btn = document.getElementById("show-hide-pass-btn");
    show_hide_pass_btn.addEventListener('click', show_hide_pass);

    show_hide_pass_conf_btn = document.getElementById("show-hide-confirm-pass-btn");
    show_hide_pass_conf_btn.addEventListener('click', show_hide_conf_pass);

    document.getElementById("password-input").addEventListener('keyup', check_password);
    document.getElementById("confirm-pass-input").addEventListener('keyup', check_password);

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

function show_hide_dogpr(e){
    if(e.target.checked === true){
        document.getElementById('dogpr').style.display = 'block';
        document.getElementById('message15').innerHTML = "";
        dog_keeping = true;
    }else{
        document.getElementById('dogpr').style.display = 'none';
        dog_keeping = false;
    }
}

function show_hide_catpr(e){
    if(e.target.checked === true){
        document.getElementById('catpr').style.display = 'block';
        document.getElementById('message15').innerHTML = "";
        cat_keeping = true;
    }else{
        document.getElementById('catpr').style.display = 'none';
        cat_keeping = false;
    }
}


function manage_property(){

    if(document.getElementById("pet_keeper_property").value === 'EXTERIOR'){
        document.getElementById('cat-keeper-ck-box').checked = false;
        document.getElementById('cat-keeper-ck-box').disabled = true;
        document.getElementById('catpr').style.display = 'none';
        cat_keeping = false;
        if(btn_map.get(hosting_btn) === true) {
            document.getElementById('dog-keeper-ck-box').disabled = false;
        }
    }else{
        if(btn_map.get(hosting_btn) === true) {
            document.getElementById('cat-keeper-ck-box').disabled = false;
            document.getElementById('catpr').style.display = 'none';
            document.getElementById('dog-keeper-ck-box').disabled = false;
            cat_keeping = false;
        }

        if(curr_user.hosting === 'CAT_KEEPING' || curr_user.hosting === 'BOTH'){
            document.getElementById('cat-keeper-ck-box').checked = true;
            document.getElementById('catpr').style.display = 'block';
            cat_keeping = true;
        }

    }
}

function done_changes(e){
    e.preventDefault();
    if (!e.submitter || e.submitter.id !== 'done-btn') {
        return false;
    }

    if(curr_user.user_type === 'PET_KEEPER'){
        if(cat_keeping === false && dog_keeping === false){
            document.getElementById('message15').innerHTML = 'Select at least one';
            return false;
        }
    }

    let data = new FormData(form);

    let form_object = {};
    data.forEach(function(value, key){
        form_object[key] = value;
    });

    console.log(form_object);

    console.log()
    for([key, value] of Object.entries(tmp_changes)){
        if(form_object[key] === undefined){
            form_object[key] = tmp_changes[key];
        }
    }

    let params = JSON.stringify(form_object);
    console.log(params);

    const xhr = new XMLHttpRequest();
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                alert('info changed!');
                refresh();
            }else if(xhr.status === 500){
                alert("exception")
            }else{
                alert('unknown status')
            }
        }
    }
    xhr.open('POST', 'ChangeInfo');
    xhr.send(params);
    return false;
}


function refresh(){
    if (document.visibilityState === 'visible') {
        location.reload();
    }

}

function printButtonStates() {
    const stateObject = {};
    btn_map.forEach((value, key) => {
        stateObject[key.id] = value;
    });
    console.log(stateObject);
}

function change_fname(){
    if(f_name_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('f-name-input').disabled = false;
        document.getElementById('f-name-input').value = '';
        f_name_btn.innerHTML = 'Cancel';
        btn_map.set(f_name_btn, true);
    }else{
        // restore the data
        f_name_btn.innerHTML = 'Change';
        document.getElementById('f-name-input').disabled = true;
        document.getElementById('f-name-input').value = curr_user.firstname;
        btn_map.set(f_name_btn, false);
    }

}

function change_lname(){
    if(l_name_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('l-name-input').disabled = false;
        document.getElementById('l-name-input').value = '';
        l_name_btn.innerHTML = 'Cancel';
        btn_map.set(l_name_btn, true);
    }else{
        // restore the data
        l_name_btn.innerHTML = 'Change';
        document.getElementById('l-name-input').disabled = true;
        document.getElementById('l-name-input').value = curr_user.lastname;
        btn_map.set(l_name_btn, false);
    }
}

function change_byear(){
    if(birth_year_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('birthdate-input').disabled = false;
        birth_year_btn.innerHTML = 'Cancel';
        btn_map.set(birth_year_btn, true);
    }else{
        // restore the data
        birth_year_btn.innerHTML = 'Change';
        document.getElementById('birthdate-input').disabled = true;
        document.getElementById('birthdate-input').value = curr_user.birthdate;
        btn_map.set(birth_year_btn, false);
    }
}

function change_gender(){
    if(gender_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('male-radio').disabled = false;
        document.getElementById('female-radio').disabled = false;
        document.getElementById('other-radio').disabled = false;
        gender_btn.innerHTML = 'Cancel';
        btn_map.set(gender_btn, true);
    }else{
        // restore the data
        gender_btn.innerHTML = 'Change';
        document.getElementById('male-radio').disabled = true;
        document.getElementById('female-radio').disabled = true;
        document.getElementById('other-radio').disabled = true;
        if(curr_user.gender === 'MALE'){
            document.getElementById('male-radio').checked = true;
        }else if(curr_user.gender === 'FEMALE'){
            document.getElementById('female-radio').checked = true;
        }else{
            document.getElementById('other-radio').checked = true;
        }
        btn_map.set(gender_btn, false);
    }
}

function change_property(){
    if(property_btn.innerHTML === 'Change'){
        manage_property();
        // allow new input
        document.getElementById('pet_keeper_property').disabled = false;
        property_btn.innerHTML = 'Cancel';
        btn_map.set(property_btn, true);
    }else{
        // restore the data
        property_btn.innerHTML = 'Change';
        document.getElementById('pet_keeper_property').disabled = true;
        document.getElementById('pet_keeper_property').value = curr_user.property;
        document.getElementById('dog-keeper-ck-box').disabled = true;
        document.getElementById('cat-keeper-ck-box').disabled = true;
        if(curr_user.hosting === 'DOG_KEEPING'){
            document.getElementById('dog-keeper-ck-box').checked = true;
            document.getElementById('cat-keeper-ck-box').checked = false;
            document.getElementById('dogpr').style.display = 'block';
            document.getElementById('catpr').style.display = 'none';
            dog_keeping = true;
            cat_keeping = false;
        }else if(curr_user.hosting === 'CAT_KEEPING'){
            document.getElementById('cat-keeper-ck-box').checked = true;
            document.getElementById('dog-keeper-ck-box').checked = false;

            document.getElementById('dogpr').style.display = 'none';
            document.getElementById('catpr').style.display = 'block';
            dog_keeping = false;
            cat_keeping = true;

        }else{
            document.getElementById('dog-keeper-ck-box').checked = true;
            document.getElementById('cat-keeper-ck-box').checked = true;
            document.getElementById('dogpr').style.display = 'block';
            document.getElementById('catpr').style.display = 'block';
            dog_keeping = true;
            cat_keeping = true;
        }

        btn_map.set(property_btn, false);
    }
}

function change_hosting(){
    if(hosting_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('dog-keeper-ck-box').disabled = false;
        if(document.getElementById("pet_keeper_property").value !== 'EXTERIOR'){
            document.getElementById('cat-keeper-ck-box').disabled = false;
        }

        hosting_btn.innerHTML = 'Cancel';
        btn_map.set(hosting_btn, true);
    }else{
        // restore the data

        document.getElementById('dog-keeper-ck-box').disabled = true;
        document.getElementById('cat-keeper-ck-box').disabled = true;
        if(curr_user.hosting === 'DOG_KEEPING'){
            document.getElementById('dog-keeper-ck-box').checked = true;
            document.getElementById('cat-keeper-ck-box').checked = false;
            document.getElementById('dogpr').style.display = 'block';
            document.getElementById('catpr').style.display = 'none';
            dog_keeping = true;
            cat_keeping = false;
        }else if(curr_user.hosting === 'CAT_KEEPING'){
            if(document.getElementById('pet_keeper_property').value === 'EXTERIOR') {
                document.getElementById('cat-keeper-ck-box').checked = false;
                document.getElementById('catpr').style.display = 'none';
                cat_keeping = false;
            }else{
                document.getElementById('cat-keeper-ck-box').checked = true;
                document.getElementById('catpr').style.display = 'block';
                cat_keeping = true;
            }
            document.getElementById('dog-keeper-ck-box').checked = false;
            document.getElementById('dogpr').style.display = 'none';
            dog_keeping = false;

        }else{
            document.getElementById('dog-keeper-ck-box').checked = true;
            document.getElementById('cat-keeper-ck-box').checked = true;
            document.getElementById('dogpr').style.display = 'block';
            document.getElementById('catpr').style.display = 'block';
            dog_keeping = true;
            cat_keeping = true;
        }


        hosting_btn.innerHTML = 'Change';
        btn_map.set(hosting_btn, false);
    }
}

function change_dogprice(){
    if(dogprice_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('dog-price-input').disabled = false;
        document.getElementById('dog-price-input').value = '';
        dogprice_btn.innerHTML = 'Cancel';
        btn_map.set(dogprice_btn, true);
    }else{
        // restore the data
        dogprice_btn.innerHTML = 'Change';
        document.getElementById('dog-price-input').disabled = true;
        document.getElementById('dog-price-input').value = curr_user.dogprice;
        btn_map.set(dogprice_btn, false);
    }
}

function change_catprice(){
    if(catprice_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('cat-price-input').disabled = false;
        document.getElementById('cat-price-input').value = '';
        catprice_btn.innerHTML = 'Cancel';
        btn_map.set(catprice_btn, true);
    }else{
        // restore the data
        catprice_btn.innerHTML = 'Change';
        document.getElementById('cat-price-input').disabled = true;
        document.getElementById('cat-price-input').value = curr_user.catprice;
        btn_map.set(catprice_btn, false);
    }
}

function change_description(){
    if(description_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('text-description').disabled = false;
        document.getElementById('text-description').value = '';
        description_btn.innerHTML = 'Cancel';
        btn_map.set(description_btn, true);
    }else{
        // restore the data
        description_btn.innerHTML = 'Change';
        document.getElementById('text-description').disabled = true;
        document.getElementById('text-description').value = curr_user.propertydescription;
        btn_map.set(description_btn, false);
    }
}

function change_country(){
    if(country_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('country').disabled = false;
        country_btn.innerHTML = 'Cancel';
        btn_map.set(country_btn, true);
    }else{
        // restore the data
        country_btn.innerHTML = 'Change';
        document.getElementById('country').disabled = true;
        document.getElementById('country').value = curr_user.country;
        btn_map.set(country_btn, false);
    }
}

function change_city(){
    if(city_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('city').disabled = false;
        document.getElementById('city').value = '';
        city_btn.innerHTML = 'Cancel';
        btn_map.set(city_btn, true);
    }else{
        // restore the data
        city_btn.innerHTML = 'Change';
        document.getElementById('city').disabled = true;
        document.getElementById('city').value = curr_user.city;
        btn_map.set(city_btn, false);
    }
}

function change_address(){
    if(address_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('street-address').disabled = false;
        document.getElementById('street-address').value = '';
        address_btn.innerHTML = 'Cancel';
        btn_map.set(address_btn, true);
    }else{
        // restore the data
        address_btn.innerHTML = 'Change';
        document.getElementById('street-address').disabled = true;
        document.getElementById('street-address').value = curr_user.address;
        btn_map.set(address_btn, false);
    }
}

function change_telephone(){
    if(telephone_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('phone-number-input').disabled = false;
        document.getElementById('phone-number-input').value = '';
        telephone_btn.innerHTML = 'Cancel';
        btn_map.set(telephone_btn, true);
    }else{
        // restore the data
        telephone_btn.innerHTML = 'Change';
        document.getElementById('phone-number-input').disabled = true;
        document.getElementById('phone-number-input').value = curr_user.telephone;
        btn_map.set(telephone_btn, false);
    }
}

function change_personal_page(){
    if(personal_page_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('url-input').disabled = false;
        document.getElementById('url-input').value = '';
        personal_page_btn.innerHTML = 'Cancel';
        btn_map.set(personal_page_btn, true);
    }else{
        // restore the data
        personal_page_btn.innerHTML = 'Change';
        document.getElementById('url-input').disabled = true;
        document.getElementById('url-input').value = curr_user.personalpage;
        btn_map.set(personal_page_btn, false);
    }
}

function change_job(){
    if(job_btn.innerHTML === 'Change'){
        // allow new input
        document.getElementById('job-input').disabled = false;
        document.getElementById('job-input').value = '';
        job_btn.innerHTML = 'Cancel';
        btn_map.set(job_btn, true);
    }else{
        // restore the data
        job_btn.innerHTML = 'Change';
        document.getElementById('job-input').disabled = true;
        document.getElementById('job-input').value = curr_user.job;
        btn_map.set(job_btn, false);
    }
}











function disable_inputs(){
    let inputs = form.getElementsByTagName('input');
    document.getElementById('text-description').disabled = true;
    document.getElementById('pet_keeper_property').disabled = true;
    document.getElementById('country').disabled = true;
    for (let i = 0; i < inputs.length; i++) {
        if(inputs[i].type !== 'submit')
            inputs[i].disabled = true;
    }
}

function fill_fields_with_info(){
    document.getElementById('f-name-input').value = curr_user.firstname;
    document.getElementById('l-name-input').value = curr_user.lastname;
    document.getElementById('birthdate-input').value = curr_user.birthdate;
    if(curr_user.gender === 'FEMALE'){
        document.getElementById('female-radio').checked = true;
    }else if(curr_user.gender === 'MALE'){
        document.getElementById('male-radio').checked = true;
    }else{
        document.getElementById('other-radio').checked = true;
    }

    if(curr_user.user_type === 'PET_KEEPER') {
        if(curr_user.property === 'INTERIOR'){
            document.getElementById('interior-option').selected = true;
        }else if(curr_user.property === 'EXTERIOR'){
            document.getElementById('exterior-option').selected = true;
        }else{
            document.getElementById('both-option').selected = true;
        }

        if(curr_user.hosting === 'DOG_KEEPING'){
            document.getElementById('dog-keeper-ck-box').checked = true;
            document.getElementById('cat-keeper-ck-box').checked = false;
            document.getElementById('catpr').style.display = 'none';
            cat_keeping = false;
        }else if(curr_user.hosting === 'CAT_KEEPING'){
            document.getElementById('dog-keeper-ck-box').checked = false;
            document.getElementById('cat-keeper-ck-box').checked = true;
            document.getElementById('dogpr').style.display = 'none';
            dog_keeping = false;
        }else{
            document.getElementById('cat-keeper-ck-box').checked = true;
            document.getElementById('dog-keeper-ck-box').checked = true;
        }

        document.getElementById('dog-price-input').value = curr_user.dogprice;
        document.getElementById('cat-price-input').value = curr_user.catprice;
        document.getElementById('text-description').value = curr_user.propertydescription;
        
    }

    document.getElementById('country').value = curr_user.country;
    document.getElementById('city').value = curr_user.city;
    document.getElementById('street-address').value = curr_user.address;
    document.getElementById('url-input').value = curr_user.personalpage;
    document.getElementById('job-input').value = curr_user.job;
    document.getElementById('phone-number-input').value = curr_user.telephone;

}


function hide_or_show_keepers_options(){
    if(curr_user.user_type === 'PET_OWNER'){
        document.getElementById('prop').style.display = 'none';
        document.getElementById('keeper_host_ck_box').style.display = 'none';
        document.getElementById('dogpr').style.display = 'none';
        document.getElementById('catpr').style.display = 'none';
        dog_keeping = false;
        cat_keeping = false;
        document.getElementById('description').style.display = 'none';
    }else{
        document.getElementById('prop').style.display = 'block';
        document.getElementById('keeper_host_ck_box').style.display = 'block';
        document.getElementById('dogpr').style.display = 'block';
        document.getElementById('catpr').style.display = 'block';
        cat_keeping = true;
        dog_keeping = true;
        document.getElementById('description').style.display = 'block';
    }
}


function change_info(e){
    e.preventDefault();
    data = new FormData(form);
    data = Array.from(data.entries());

    console.log(data)
}

// noinspection DuplicatedCode
function get_userdata_settings(){
    const xhr = new XMLHttpRequest();
    let tmp_data
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                tmp_data = JSON.parse(xhr.responseText);
            }else{
                alert('unknown code');
            }
        }
    }

    xhr.open('GET', 'GetLoggedInUser', false);
    xhr.send();
    return tmp_data;
}
