const getDelay = () => Math.floor((Math.random()*1000)+500);

let counter;

function printDone(){
    counter--;
    if (counter === 0){
        console.log("Done!")
    }
}

function printAsync(s, cb) {
    setTimeout(function() {
        console.log(s);
        if (cb) cb();
    }, getDelay());
}

function inparallel(list, D){
    counter = list.length;
    list.forEach(fun => fun(D))
}


const list = [
    (cb) => printAsync("A", cb),
    (cb) => printAsync("B", cb),
    (cb) => printAsync("C", cb),
];

inparallel(list, printDone)


