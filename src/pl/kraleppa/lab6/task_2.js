import waterfall from 'async/waterfall.js';

const sequence = [
    (arg, callback) => callback(null, arg+"2"),
    (arg, callback) => callback(null, arg+"3"),
    (arg, callback) => callback(null, arg+"1"),
]

function loop(n){
    let taskList = [(callback) => callback(null, "1")]
    for (let i = 0; i < n; i++){
        taskList = taskList.concat(sequence)
    }
    taskList.pop();
    waterfall(taskList, (err, result) => console.log(result));
    console.log("Done!")
}

loop(7)