const header = document.querySelector("header");
const btnTop = document.querySelector(".btn-top");
const gnb = document.querySelector(".gnb");

btnTop.addEventListener("click", function () {
  window.scroll({
    top: 0,
    behavior: "smooth",
  });
});

document.addEventListener("scroll", function () {
  const scrollY = window.scrollY;
  const btnShowHeight = 300;

  if (scrollY > 0) {
    header.classList.add("scroll");
  } else {
    header.classList.remove("scroll");
  }

  if (window.scrollY > btnShowHeight) {
    btnTop.classList.add("show");
  } else {
    btnTop.classList.remove("show");
  }
});

gnb.addEventListener("mouseenter", function () {
  header.classList.add("over");
});

gnb.addEventListener("mouseleave", function () {
  console.log("off");
  header.classList.remove("over");
});
