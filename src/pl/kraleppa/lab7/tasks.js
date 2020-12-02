var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(cb) {
    let timeToWait = 1;
    const getFork = () => {
        if (this.state === 1){
            timeToWait *= 2;
            setTimeout(getFork, timeToWait)
        } else {
            this.state = 1;
            if (cb) cb();
        }
    }

    getFork();
}

Fork.prototype.release = function(cb) {
    this.state = 0;
    if (cb) cb();
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    return this;
}

const showForks = () => {
    console.log(forks.map(fork => fork.state))
}

Philosopher.prototype.startNaive = function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

        const eatingProcess = (id, count) => {
            console.log(`Philosopher ${id} entering`)
            showForks()
            forks[f1].acquire(() => {
                console.log(`Philosopher ${id} has 1st fork`)
                showForks()
                forks[f2].acquire(() => {
                    console.log(`Philosopher ${id} has 2nd fork`)
                    showForks()
                    console.log(`Philosopher ${id} starts to eat`)
                    showForks()
                    setTimeout(() => {
                        console.log(`Philosopher ${id} ends eating`)
                        showForks()
                        forks[f1].release(() => {
                            console.log(`Philosopher ${id} release 1st fork`)
                            showForks()
                            forks[f2].release(() => {
                                console.log(`Philosopher ${id} release 2nd fork`)
                                showForks()
                                count--;
                                if (count > 0){
                                    setTimeout(() => eatingProcess(id, count), 2)

                                }
                            })
                        }, 2)
                    })
                })
            })
        }

        setTimeout(() => eatingProcess(id, count), 0)
}

Philosopher.prototype.startAsym = function(count) {
    var forks = this.forks,
        id = this.id,
        f1 = id % 2 === 0 ? this.f1 : this.f2,
        f2 = id % 2 === 0 ? this.f2 : this.f1;

    const eatingProcess = (id, count) => {
        console.log(`Philosopher ${id} entering`)
        showForks()
        forks[f1].acquire(() => {
            console.log(`Philosopher ${id} has 1st fork`)
            showForks()
            forks[f2].acquire(() => {
                console.log(`Philosopher ${id} has 2nd fork`)
                showForks()
                console.log(`Philosopher ${id} starts to eat`)
                showForks()
                setTimeout(() => {
                    console.log(`Philosopher ${id} ends eating`)
                    showForks()
                    forks[f1].release(() => {
                        console.log(`Philosopher ${id} release 1st fork`)
                        showForks()
                        forks[f2].release(() => {
                            console.log(`Philosopher ${id} release 2nd fork`)
                            showForks()
                            count--;
                            if (count > 0){
                                setTimeout(() => eatingProcess(id, count), 2)

                            }
                        })
                    }, 2)
                })
            })
        })
    }

    setTimeout(() => eatingProcess(id, count), 0)

}
var waitingCount = 4;

Philosopher.prototype.startConductor = function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;



    const eatingProcess = (id, count) => {
        console.log(`Philosopher ${id} entering ${waitingCount}`)
        showForks()
        if (waitingCount === 0){
            setTimeout(() => eatingProcess(id, count), 2);
        } else {
            waitingCount--;
            forks[f1].acquire(() => {
                console.log(`Philosopher ${id} has 1st fork ${waitingCount}`)
                showForks()
                forks[f2].acquire(() => {
                    console.log(`Philosopher ${id} has 2nd fork ${waitingCount}`)
                    showForks()
                    console.log(`Philosopher ${id} starts to eat ${waitingCount}`)
                    showForks()
                    setTimeout(() => {
                        console.log(`Philosopher ${id} ends eating ${waitingCount}`)
                        showForks()
                        forks[f1].release(() => {
                            console.log(`Philosopher ${id} release 1st fork ${waitingCount}`)
                            showForks()
                            forks[f2].release(() => {
                                waitingCount++;
                                console.log(`Philosopher ${id} release 2nd fork ${waitingCount}`)
                                showForks()
                                count--;
                                if (count > 0){
                                    setTimeout(() => eatingProcess(id, count), 2)
                                }
                            })
                        }, 2)
                    })
                })
            })
        }
    }

    setTimeout(() => eatingProcess(id, count), 0)
}


var N = 5;
var forks = [];
var philosophers = []
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startConductor(1000);
}

