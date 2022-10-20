/**
 * 
 */
 
 
 //메모부분
let addBox = document.querySelector(".memoadd-box");
let popupBox = document.querySelector(".memopopup-box");
let clsicon = popupBox.querySelector("#clsicon");

addBox.addEventListener("click", () => {
    popupBox.classList.add("show");
});

clsicon.addEventListener("click", () => {
    popupBox.classList.remove("show");
});





