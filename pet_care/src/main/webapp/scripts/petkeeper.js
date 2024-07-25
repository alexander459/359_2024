let curr_user;

window.onload = function(){
    console.log('pet keepers page loaded')

    if(curr_user === undefined){
        alert('you are not logged in');
        window.open('visitor.html', '_self');
        return;
    }

    document.getElementById('settings-btn').addEventListener('click', open_settings_page)
    curr_user = get_keeper_data();
    refresh();

}

function refresh(){
    document.addEventListener('visibilitychange', function() {
        if (document.visibilityState === 'visible') {
            location.reload();
        }
    });
}

function open_settings_page(){
    window.open('settings.html', '_self')
}

function get_keeper_data(){
    const xhr = new XMLHttpRequest();
    let tmp_data
    xhr.onload = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200) {
                console.log(xhr.responseText)
                tmp_data = JSON.parse(xhr.responseText);
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

    xhr.open('GET', 'GetLoggedInUser', true);

    xhr.send();
    return tmp_data;
}
