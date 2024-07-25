let weak = false;
let lon,lat,address;
let map_ok = true;
let show_hide_pass_btn;
let show_hide_pass_conf_btn;
let form;
let mode;
let is_dog_checked;
let is_cat_checked;

window.onload = function () {
    //hide the pet keeper's fields
    hide_pet_keeper_fields();
    form = document.getElementById("sign-up-form");
    form.addEventListener('submit', sign_up);

    show_hide_pass_btn = document.getElementById("show-hide-pass-btn");
    show_hide_pass_btn.addEventListener('click', show_hide_pass);

    show_hide_pass_conf_btn = document.getElementById("show-hide-confirm-pass-btn");
    show_hide_pass_conf_btn.addEventListener('click', show_hide_conf_pass);

    document.getElementById("password-input").addEventListener('keyup', check_password);
    document.getElementById("confirm-pass-input").addEventListener('keyup', check_password);
}

function hide_pet_keeper_fields(){
    document.getElementById("prop").style.display = 'none';
    document.getElementById("keeper_host_ck_box").style.display = 'none';
    document.getElementById("dogpr").style.display = 'none';
    document.getElementById("catpr").style.display = 'none';
    document.getElementById("description").style.display = 'none';
}

function show_pet_keeper_fields(){
    document.getElementById("prop").style.display='block';
    document.getElementById("keeper_host_ck_box").style.display='block';
    document.getElementById("dogpr").style.display='block';
    document.getElementById("catpr").style.display='block';
    document.getElementById("description").style.display='block';
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
        return false
    }
    return true;
}

function Strength() {
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

function changes(e){
    let selectedOption = (e.target).options[(e.target).selectedIndex].value;
    if(selectedOption === "PET_KEEPER"){     //if keeper
        changes_keeper();
    }
    else{     //if owner
        changes_owner();
    }
}

function check_dog_keeper_checkbox(e){
    if(e.target.checked) {
        document.getElementById("message15").innerHTML = "";
        document.getElementById("dog-price-input").setAttribute('required', 'required');
        is_dog_checked = true;
    }else{
        document.getElementById("dog-price-input").removeAttribute('required');
        is_dog_checked = false;
    }

    if(!is_dog_checked && !is_cat_checked){
        document.getElementById("message15").innerHTML = "No option for pet hosting selected";
    }
}

function check_cat_keeper_checkbox(e) {
    if(e.target.checked) {
        document.getElementById("message15").innerHTML = "";
        document.getElementById("cat-price-input").setAttribute('required', 'required');
        is_cat_checked = true;
    }else{
        document.getElementById("cat-price-input").removeAttribute('required');
        is_cat_checked = false;
    }

    if(!is_dog_checked && !is_cat_checked){
        document.getElementById("message15").innerHTML = "No option for pet hosting selected";
    }
}


function check_property(e){
    if(e.target.value === 'EXTERIOR'){
        document.getElementById("dog-keeper-ck-box").disabled = false;
        document.getElementById("cat-keeper-ck-box").disabled = true;
    }else if(e.target.value === 'INTERIOR'){
        document.getElementById("dog-keeper-ck-box").disabled = false;
        document.getElementById("cat-keeper-ck-box").disabled = false;
    }else{
        document.getElementById("dog-keeper-ck-box").disabled = false;
        document.getElementById("cat-keeper-ck-box").disabled = false;
    }
}

function changes_keeper() {
    //makes changes if the user is a pet keeper
    is_cat_checked = false;
    is_dog_checked = false;
    document.getElementById('default-option').selected = true;

    document.getElementById('dog-price-input').value = "";
    document.getElementById('cat-price-input').value = "";

    document.getElementById("pet_keeper_property").setAttribute('required','required');
    document.getElementById("pet_keeper_property").addEventListener('change', check_property);
    document.getElementById("dog-keeper-ck-box").checked = false;
    document.getElementById("cat-keeper-ck-box").checked = false;
    document.getElementById("dog-keeper-ck-box").disabled = true;
    document.getElementById("cat-keeper-ck-box").disabled = true;

    document.getElementById("dog-keeper-ck-box").addEventListener("change", check_dog_keeper_checkbox);
    document.getElementById("cat-keeper-ck-box").addEventListener("change", check_cat_keeper_checkbox);

    show_pet_keeper_fields()
}

function changes_owner() {
    //makes changes if the user is a pet owner
    document.getElementById("pet_keeper_property").removeAttribute('required');
    document.getElementById("dog-keeper-ck-box").removeEventListener("change", check_dog_keeper_checkbox);
    document.getElementById("cat-keeper-ck-box").removeEventListener("change", check_cat_keeper_checkbox);
    hide_pet_keeper_fields();
}

document.getElementById("Map").style.display='none';

function sentDataToAPI(){
    map_ok = true;
    let countryElement = document.getElementById("country");
    let country = countryElement.options[countryElement.selectedIndex].value;
    let city = document.getElementById("city").value;
    address = document.getElementById("street-address").value;
    document.getElementById("message13").innerHTML = "";
    document.getElementById("message14").innerHTML = "";
    const data = null;
    document.getElementById("Map").style.display='none';
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            let response = JSON.parse(this.responseText)[0];
            if(response === undefined){
                document.getElementById("message13").innerHTML = "This location doesn't exists.";
                map_ok = false;
            }
            else{
                lat = response.lat;
                lon = response.lon;
                let display_name = (response.display_name).toUpperCase();
                if(!display_name.includes("HERAKLION")){
                    document.getElementById("message14").innerHTML = "This service is only available in Heraklion.";
                }
                else{
                    document.getElementById("Map").style.display='block';
                    open_map();
                }
            }
        }
    });

    xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/forward?street="+address+"&city="+city+"&country="+country+"&accept-language=en&polygon_threshold=0.0");
    xhr.setRequestHeader("X-RapidAPI-Key", "db70496799msh6dbc4ae7ce533a1p15a489jsnb911f54a8e73");
    xhr.setRequestHeader("X-RapidAPI-Host", "forward-reverse-geocoding.p.rapidapi.com");

    xhr.send(data);
}

function open_map(){
    let map = new OpenLayers.Map("Map");
    let mapnik = new OpenLayers.Layer.OSM();
    map.addLayer(mapnik);
    let fromProjection = new OpenLayers.Projection("EPSG:4326"); // Transform from WGS 1984
    let toProjection = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
    let position = new OpenLayers.LonLat(lon, lat).transform(fromProjection, toProjection);
    let zoom = 16;
    var markers = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(markers);
    markers.addMarker(new OpenLayers.Marker(position));
    map.setCenter (position, zoom);
    let popup = new OpenLayers.Popup.FramedCloud("Popup", position, null, address, null, true);
    map.addPopup(popup);
}

function sign_up() {
    event.preventDefault();
    if(document.getElementById('user').value === 'PET_KEEPER') {
        if (!is_dog_checked && !is_cat_checked) {
            document.getElementById("message15").innerHTML = "No option for pet hosting selected";
            return false;
        }
    }

    if (!check_password())
        return false;

    if (weak === false) {
        document.getElementById("submit_result").innerHTML = "Registration succeed!";
    }
    else {
        document.getElementById("submit_result").innerHTML = "Registration failed!";
        return false;
    }

    if(document.getElementById("cat-price-input").value === ""){
        document.getElementById("cat-price-input").value = 0.0;
    }

    if(document.getElementById("dog-price-input").value === ""){
        document.getElementById("dog-price-input").value = 0.0;
    }

    let data = new FormData(form)
    let params = parse_params(data)

    console.log(params)
    const xhr = new XMLHttpRequest();
    xhr.onload=function(){
        if(xhr.readyState===4){
            if(xhr.status===500){
                alert("Exception in server")
            }else if(xhr.status===400){
                alert("Username Exists")
            }else if(xhr.status===401){
                alert("Email Exists")
            }else if(xhr.status===402){
                alert("Phone Exists")
            }else if(xhr.status === 200) {
                form.reset()
                alert('welcome pet owner')
                window.open('petowner.html', '_self')
            }else if(xhr.status === 201){
                form.reset()
                alert('welcome pet keeper')
                window.open('petkeeper.html', '_self')
            }else{
                alert(xhr.status + " unknown status...")
            }
        }
    };
    xhr.open('POST', 'SignUp');
    xhr.send(params);
    return false

}


function parse_params(data){
    let params = "";
    let cat_keeping = document.getElementById('cat-keeper-ck-box').checked;
    let dog_keeping = document.getElementById('dog-keeper-ck-box').checked;


    data = Array.from(data.entries());
    params += "{";
    data.forEach((pair, index) => {

        if(pair[0] !== 'hosting' && pair[0] !== 'check' && pair[0] !== 'lat' && pair[0] !== 'lon') {
            params += "\"";
            params += pair[0];
            params += "\"";
            params += ":";
            params += "\"";
            params += pair[1];
            params += "\"";
            params += ", ";
        }

    });

    params += "\"";
    params += 'lat';
    params += "\"";
    params += ":";
    params += "\"";
    params += "0.0"
    params += "\"";
    params += ", ";

    params += "\"";
    params += 'lon';
    params += "\"";
    params += ":";
    params += "\"";
    params += "0.0"
    params += "\"";
    params += ", ";

    params += "\"";
    params += 'hosting';
    params += "\"";
    params += ":";
    params += "\"";

    if(cat_keeping && dog_keeping){
        params += "BOTH";
    }else if(cat_keeping){
        params += "CAT_KEEPING";
    }else{
        params += "DOG_KEEPING";
    }
    params += "\"";
    params += "}";

    return params;
}

