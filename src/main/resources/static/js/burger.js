document.addEventListener("DOMContentLoaded", function() {
    const menuNButton = document.querySelector(".menu-button");
    const mainuNav     = document.querySelector(".main-nav");

    if(!menuNButton || !mainuNav){
        return;
    }
    //開關
    menuNButton.addEventListener("click", function(){
        const isopen = mainuNav.classList.toggle("active")
        menuNButton.textContent = isopen ? "X" : "三";
   
            }
        )
    }
)

