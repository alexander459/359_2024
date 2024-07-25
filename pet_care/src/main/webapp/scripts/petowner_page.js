let table
let curr_user;

window.onload = function (e){
    console.log("petowner loaded");
    curr_user = get_owner_data();

    if(curr_user === undefined){
        alert('you are not logged in');
        window.open('visitor.html', '_self');
        return;
    }

    table = document.getElementById("petkeepers");
    document.getElementById("log-out-btn").addEventListener('click', logout);
    document.getElementById("settings-btn").addEventListener('click', settings);
    document.getElementById("my-pet-btn").addEventListener('click', function (){
        window.open("mypet.html", "_self");
    });

    document.addEventListener('visibilitychange', function() {
        if (document.visibilityState === 'visible') {
            location.reload();
        }
    });

}

function settings(){
    window.open('settings.html', '_self');
}

function logout(){
    const xhr = new XMLHttpRequest();
    console.log("log out")
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
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

function get_owner_data(){
    const xhr = new XMLHttpRequest();
    let tmp_data
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                tmp_data = JSON.parse(xhr.responseText);
                return tmp_data;
            }else if(xhr.status === 400){
                return undefined;
            }else if(xhr.status === 500){
                alert('exception');
                return undefined;
            }else{
                alert('unknown code');
                return undefined;
            }
        }
    }

    xhr.open('GET', 'GetLoggedInUser', false);

    xhr.send();
    return tmp_data;
}