let pet_form;
let pet_profile;
let cur_user;
let pet_data;

window.onload = function(){
    console.log("mypet page loaded");


    get_userdata();
    cur_user = JSON.parse(cur_user);

    pet_form = document.getElementById("pet-registration-form");
    pet_profile = document.getElementById("pet-profile");

    pet_form.style.display = 'none';
    pet_profile.style.display = 'none';

    pet_form.addEventListener('submit', insert_pet);

    document.getElementById('pet-description').addEventListener('keydown', disable_newline);
    document.getElementById("pet-id-input").addEventListener('keypress', check_id_input)
    document.getElementById("remove-pet-btn").addEventListener('click', remove_pet);
    document.getElementById('image').addEventListener('click', viewImageFullScreen);
    get_pet()
}

function disable_newline(e){
    if(e.key === 'Enter'){
        e.preventDefault();
        console.log("new line")
    }
}

function remove_pet(){
    //servlet job and if success
    show_pet_registration_form();
    const xhr = new XMLHttpRequest();
    xhr.onload = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                pet_form.reset();
            }else if(xhr.status === 500){
                alert('exception');
            }else{
                alert('unknown error');
            }
        }
    }
    xhr.open('POST', 'DeletePet');
    xhr.send(JSON.stringify(pet_data));
}

function check_id_input(e){
    if(e.which < 48 || e.which > 57)
        e.preventDefault();
}




function insert_pet(){
    event.preventDefault();
    let data = new FormData(pet_form);
    let params = parse_params(data);
    const xhr = new XMLHttpRequest();

    console.log(params)
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                pet_data = JSON.parse(params);
                show_pet();
            }else if(xhr.status === 400){
                alert("pet id exists");
            }else if(xhr.status === 500){
                alert("exception");
            }else{
                alert("unknown code");
            }
        }
    }

    xhr.open('POST', 'RegisterPet');
    xhr.send(params);
}


function parse_params(data){
    let params = "";

    data = Array.from(data.entries());
    params += "{";

    data.forEach((pair, index) => {
        params += "\"";
        params += pair[0];
        params += "\"";
        params += ":";
        params += "\"";
        params += pair[1];
        params += "\"";
        params += ", ";

    });

    params += "\"owner_id\":\"";
    params += cur_user["owner_id"];
    params += "\"";


    params += "}";

    return params;
}

/* code find online */
function viewImageFullScreen() {
    var image = document.getElementById('image');
    if (image.requestFullscreen) {
        image.requestFullscreen();
    } else if (image.mozRequestFullScreen) { /* Firefox */
        image.mozRequestFullScreen();
    } else if (image.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
        image.webkitRequestFullscreen();
    } else if (image.msRequestFullscreen) { /* IE/Edge */
        image.msRequestFullscreen();
    }
}


function show_pet(){
    console.log(pet_data);

    pet_form.style.display = 'none';
    pet_profile.style.display = 'block';

    document.getElementById("image").src = pet_data.photo;
    document.getElementById('pet-name-span').innerHTML = pet_data.name;

    if(pet_data.type === 'DOG')
        document.getElementById('pet-type-span').innerHTML = 'Dog';
    else
        document.getElementById('pet-type-span').innerHTML = 'Cat';

    document.getElementById('birth-year-span').innerHTML = pet_data.birthyear;

    if(pet_data.gender === 'MALE')
        document.getElementById('pet-gender-span').innerHTML = 'Male';
    else
        document.getElementById('pet-gender-span').innerHTML = 'Female';


    document.getElementById('pet-weight-span').innerHTML = pet_data.weight;

    if(pet_data.breed === 'AEGEAN')
        document.getElementById('pet-breed-span').innerHTML = 'Aegean';
    else if(pet_data.breed === 'LABRADOR')
        document.getElementById('pet-breed-span').innerHTML = 'Labrador';
    else
        document.getElementById('pet-breed-span').innerHTML = 'Turkish';


    document.getElementById('pet-id-span').innerHTML = pet_data.pet_id;
    document.getElementById('pet-description-span').innerHTML = pet_data.description;
}

function show_pet_registration_form(){
    pet_form.style.display = 'block';
    pet_profile.style.display = 'none';
}

function get_pet(){
    const xhr = new XMLHttpRequest();

    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                pet_data = JSON.parse(xhr.responseText);
                show_pet();
            }else if(xhr.status === 401){
                show_pet_registration_form();
            }else if(xhr.status === 500){
                alert("Exception");
            }else{
                alert("unknown status");
            }
        }
    }

    xhr.open('GET', 'GetPet');
    xhr.send();

}

function get_userdata(){
    const xhr = new XMLHttpRequest();

    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                cur_user = xhr.responseText;
                console.log(cur_user);
            }
        }
    }

    xhr.open('GET', 'GetLoggedInUser', false);
    xhr.send();
}
