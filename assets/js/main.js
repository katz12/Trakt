var start = function() {

    createMagnetButton();
};

var createMagnetButton = function() {
    var titles = document.getElementsByClassName("fanart");
    for (var i = 0; i < titles.length; i++) {
        var title = titles[i];
        var button = document.createElement('div');
        button.setAttribute('id', 'magnet');
        button.style.width = '60px';
        button.style.height = '60px';
        button.style.position = 'absolute';
        button.style.left = '12px';
        button.style.top = '10px';
        button.style.backgroundColor='#FFB750';
        title.appendChild(button);
    }

};

start();