let form;

window.onload = function(){
    console.log('adminlogin page loaded');
    form = document.getElementById('admin-login-form');
    form.addEventListener('submit', login_admin);
}

function login_admin(e){
    e.preventDefault();

    let params;
    let data = new FormData(form);
    let form_object = {};

    data.forEach(function(value, key){
        form_object[key] = value;
    });

    params = JSON.stringify(form_object);
    console.log(params);

    const xhr = new XMLHttpRequest();
    xhr.onload = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                window.open('admin_page.html', '_self');
            }else if(xhr.status === 400){
                alert('wrong name or password');
                form.reset();
            }else if(xhr.status === 500){
                alert('exception');
            }else{
                alert('unknown status');
            }
        }
    }
    xhr.open('POST', 'AdminLogIn');
    xhr.send(params);

    return false;
}

