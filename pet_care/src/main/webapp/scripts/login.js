let form
window.onload = function () {
    console.log("login page loaded")
    form = document.getElementById("login-form")
    form.addEventListener("submit", log_in)

}


function log_in(){
    event.preventDefault()
    let data
    let params

    data = new FormData(form)
    data = Array.from(data.entries())
    params = "{"
    params += "\"" + data[0][0] + "\":\"" + data[0][1] + "\","
    params += "\"" + data[1][0] + "\":\"" + data[1][1] + "\","
    params += "\"" + data[2][0] + "\":\"" + data[2][1] + "\""
    params += "}"


    const xhr = new XMLHttpRequest()
    xhr.onload=function(){
        if(xhr.readyState===4){
            if(xhr.status === 403){
                alert('wrong username or password')
            }else if(xhr.status === 200) {
                form.reset();
                window.open('petowner_page.html', '_self');
            }else if(xhr.status === 201){
                form.reset();
                window.open('petkeeper.html', '_self');
            }else if(xhr.status === 500){
                alert('exception')
            }else{
                alert('unknown status ' + xhr.status)
            }
        }
    };
    xhr.open('POST', 'LogIn')
    xhr.send(params)
    return false
}

