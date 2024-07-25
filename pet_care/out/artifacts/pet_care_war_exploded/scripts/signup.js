let form;
let pass_input
let verify_pass_input
let valid_pass
let radio_owner
let radio_keeper
let pet_form

window.onload = function () {
    console.log("signup page loaded")

    form = document.getElementById("sign-up-form");
    form.addEventListener("submit", sign_up);

    pass_input = document.getElementById("pass-input")
    verify_pass_input = document.getElementById("verify-pass-input")
    pass_input.addEventListener("keyup", compare_pass)
    verify_pass_input.addEventListener("keyup", compare_pass)
    valid_pass = false

    radio_owner = document.getElementById("pet-owner-radio")
    radio_keeper = document.getElementById("pet-keeper-radio")
    radio_owner.addEventListener("click", show_pet_form)
    radio_keeper.addEventListener("click", hide_pet_form)

    pet_form = document.getElementById("pet-form-div")
    hide_pet_form()
}

function show_pet_form(){
    pet_form.style.display = 'block'
    document.getElementById("chip-input").required = true
    document.getElementById("pet-name-input").required = true
    document.getElementById("chip-input").addEventListener("keyup", check_chip_sid)
}

function hide_pet_form(){
    pet_form.style.display = 'none'
    document.getElementById("chip-input").required = false
    document.getElementById("pet-name-input").required = false
}

function check_chip_sid(event){
    event.target.value = event.target.value.replace(/[^0-9]/g, '')
}

function compare_pass(){
    if (pass_input.value ===  verify_pass_input.value){
        valid_pass = true
        document.getElementById("pass-input-label").style.color = "green"
        document.getElementById("verify-pass-label").style.color = "green"
    }else{
        valid_pass = false
        document.getElementById("pass-input-label").style.color = "red"
        document.getElementById("verify-pass-label").style.color = "red"
    }
}

function blink_pass_labels(){
    let l1 = document.getElementById("pass-input-label")
    let l2 =document.getElementById("verify-pass-label")

    const startTime = Date.now();
    const duration = 1200; // 3 seconds
    var loops = 0

    const interval = setInterval(() => {
        const currentTime = Date.now();
        const elapsedTime = currentTime - startTime;

        if(loops % 2 === 0) {
            l1.style.color = "black"
            l2.style.color = "black"
        }else{
            l1.style.color = "red"
            l2.style.color = "red"
        }

        loops++
        if (elapsedTime >= duration) {
            clearInterval(interval);
        }
    }, 200);
}

function sign_up(){
    event.preventDefault();
    let data
    let params

    if(valid_pass === false){
        blink_pass_labels()
        return false
    }

    data = new FormData(form)
    params = ""
    data = Array.from(data.entries())
    console.log(data)

    params += "{"
    data.forEach((pair, index) => {
        params += "\""
        params += pair[0]
        if(pair[0] === 'chip_sid'){     //this is to prevent gson error on empty string
            if(pair[1] === ""){
                pair[1] = 0
            }
        }
        params += "\""
        params += ":"
        params += "\""
        params += pair[1]
        params += "\""
        if (index < data.length - 1) {
            params += ", ";
        }
    })
    params += "}"

    console.log(params)
    const xhr = new XMLHttpRequest();

    xhr.open('POST', 'SignUp');
    xhr.send(params);
    return false
}








