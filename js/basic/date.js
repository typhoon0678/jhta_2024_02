const current = new Date();
const yoil = ["sun", "mon", "tue", "wed", "thu", "fri", "sat"];
// 내장객체
const currentDay = current.getDay();
console.log(currentDay);

//함수   f(x) = x+3;  f(3) = 8;
const plusThree = function (num) {
    return num + 3;
};
const jegop = function (num) {
    return num * num;
};
//어떤 수를 입력했을때 나머지를 구해주는 함수를 만드시오. 함수의 이름은 mod로 하시오.

const mod = function (firstNum, secondNum) {
    return firstNum % secondNum;
};

const jjang = {
    name: "장성호",
    age: 20,
    nickName: "정형돈",
    isAdult: true,
};

const myNumberFunction = {
    plusThree: function (num) {
        return num + 3;
    },
    jegop: function (num) {
        return num * num;
    },
    mod: function (firstNum, secondNum) {
        return firstNum % secondNum;
    },
};
console.log(myNumberFunction.plusThree(3));

//console.log(myNumberFunction.plusThree(3));
// console.log(plusThree(3));
// console.log(jegop(4));
// console.log(mod(10, 5));