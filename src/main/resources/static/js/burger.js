document.addEventListener("DOMContentLoaded", function() {
    const menuNButton = document.querySelector(".menu-button");
    const mainNav     = document.querySelector(".main-nav");

    if(!menuNButton || !mainNav){
        return;
    }
    //開關
    menuNButton.addEventListener("click", function(){
        const isopen = mainNav.classList.toggle("active")
        menuNButton.textContent = isopen ? "X" : "三";
   
            }
        )
    }
)

