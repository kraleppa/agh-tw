const getDelay = Math.floor((Math.random()*1000)+500);

function task1(n, i){
    if (i === n){
        console.log("Done!")
        return;
    }
    i++;
    console.log("1");
    setTimeout(() => task2(n, i), getDelay);
}

function task2(n, i){
    console.log("2");
    setTimeout(() => task3(n, i), getDelay);
}

function task3(n, i){
    console.log("3");
    setTimeout(() => task1(n, i), getDelay);
}

function loop(n){
    setTimeout(() => task1(n, 0), getDelay);
}

loop(2)