let disableResizeAllTable = () => {
    let editor = document.getElementById('editor');
    let listTable = editor.getElementsByTagName('table');
    for (let table of listTable) {
        let attr = table.getAttribute("data-type");
        if(attr === "form-info" || attr === 'form-result') {
            continue;
        }
        $(table).colResizable({
            disable: true
        });
    }
}; //disable col resize all table

let enableResizeAllTable = () => {
    let editor = document.getElementById('editor');
    let listTable = editor.getElementsByTagName('table');
    for (let table of listTable) {
        let attr = table.getAttribute("data-type");
        if(attr === "form-info" || attr === 'form-result') {
            continue;
        }
        $(table).colResizable({
            liveDrag: true,
            draggingClass: "dragging"
        });
    }
};  //enable col resize all table

document.getElementById('btn-print').onclick = () => {
    disableResizeAllTable();
    $("#print").printThis({importCSS: false});
    setTimeout(function () {
        enableResizeAllTable();
    }, 500);
}; //print report

document.getElementById('add-form-info').onclick = () => {
    addFormInfo();
};