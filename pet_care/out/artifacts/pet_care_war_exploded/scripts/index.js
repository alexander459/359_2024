window.onload = function (){

    console.log("index page loaded")

    document.getElementById("log-in-btn").addEventListener("click", function() {
        window.open("login.html", "_self");
    })

    document.getElementById("sign-up-btn").addEventListener("click", function() {
        window.open("signup.html", "_self");
    })

}


