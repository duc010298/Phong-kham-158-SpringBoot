const selectSizeTable = document.getElementById('select-table-size');
const selectSizeTableSpanList = selectSizeTable.getElementsByTagName('span');
const lineSpaceDropdown = document.getElementById('dropdown-content');
const editor = document.getElementById('editor');
const contextMenu = document.getElementById('context-menu');

let highlightFlag = false;
let firstPos;
let lastPos;

let tempNodeTable;

editor.onmousedown = (event) => {
    let target = event.target;
    if (target.nodeName !== "TD") {
        highlightFlag = false;
        let listTd = document.getElementsByTagName("TD");
        for (let td of listTd) {
            td.style.backgroundColor = null;
            td.classList.remove('highlight');
        }
    }
};

document.getElementById('line-spacing').onclick = () => {
    document.getElementById('dropdown-content').classList.toggle('show');
};

document.getElementById('insert-table').onclick = () => {
    selectSizeTable.innerHTML = '';
    document.querySelector('.table-dropdown input[name=row]').value = 5;
    document.querySelector('.table-dropdown input[name=col]').value = 5;

    for (let i = 0; i < 25; i++) {
        let s = document.createElement('span');
        addEventToSpan(s);
        selectSizeTable.appendChild(s);
    }
    selectSizeTable.style.gridTemplateColumns = 'auto auto auto auto auto';

    document.getElementById('table-dropdown-content').classList.toggle('show');
};

// Close the dropdown if the user clicks outside of it
window.onclick = (event) => {
    let target = event.target;
    if (!target.matches('.line-spacing') && !target.matches('.icon-line-spacing')) {
        document.getElementById("dropdown-content").classList.remove('show');
    }
    if (!target.matches('.insert-table') && !target.matches('.icon-table-grid')) {
        document.getElementById("table-dropdown-content").classList.remove('show');
    }
};

let getCarretNode = () => {
    let selection;
    if (window.getSelection) {
        selection = window.getSelection();
    } else if (document.selection && document.selection.type !== "Control") {
        selection = document.selection;
    }
    let anchorNode = selection.anchorNode;
    if (anchorNode === null) return null;
    return anchorNode.nodeType === 3 ? anchorNode.parentNode : anchorNode;
};

let detectPosition = (td) => {
    let col = 0;
    let row = -1;
    let colspan = td.getAttribute("colspan");
    if (colspan === null) {
        colspan = 1;
    } else {
        colspan = Number(colspan);
    }
    let rowspan = td.getAttribute("rowspan");
    if (rowspan === null) {
        rowspan = 1;
    } else {
        rowspan = Number(rowspan);
    }
    let trNode = td.parentNode;
    while (true) {
        if (trNode.nodeName === "TR") break;
        trNode = trNode.parentNode;
    }
    let listTd = trNode.getElementsByTagName("TD");
    for (let tempTd of listTd) {
        col++;
        if (td === tempTd) break;
    }

    let tBody;
    tBody = trNode.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    let listTr = tBody.getElementsByTagName("TR");
    for (let tempTr of listTr) {
        row++;
        if (trNode === tempTr) break;
    }
    return [col, row, colspan, rowspan];
};

let resetHighlight = (td) => {
    let tBodyNode = td.parentNode;
    while (true) {
        if (tBodyNode.nodeName === "TBODY") break;
        tBodyNode = tBodyNode.parentNode;
    }
    let listTd = tBodyNode.getElementsByTagName("TD");
    for (let td of listTd) {
        td.style.backgroundColor = null;
        td.classList.remove('highlight');
    }
};

let addEventToTd = (td) => {
    td.onmousedown = (event) => {
        resetHighlight(event.target);
        firstPos = detectPosition(event.target);
        lastPos = firstPos;
        highlightFlag = true;
    };
    td.onmousemove = (event) => {
        if (highlightFlag) {
            let currentPos = detectPosition(event.target);
            if (JSON.stringify(currentPos) !== JSON.stringify(lastPos)) {
                resetHighlight(event.target);
                lastPos = currentPos;

                let fCol, fRow, lCol, lRow;
                let temp1 = [firstPos[0], lastPos[0], firstPos[0] + firstPos[2] - 1, lastPos[0] + lastPos[2] - 1];
                fCol = Math.min(...temp1);
                lCol = Math.max(...temp1);
                let temp2 = [firstPos[1], lastPos[1], firstPos[1] + firstPos[3] - 1, lastPos[1] + lastPos[3] - 1];
                fRow = Math.min(...temp2);
                lRow = Math.max(...temp2);

                let tBodyNode = event.target.parentNode;
                while (true) {
                    if (tBodyNode.nodeName === "TBODY") break;
                    tBodyNode = tBodyNode.parentNode;
                }
                let listTr = tBodyNode.getElementsByTagName("TR");
                for (let i = fRow; i <= lRow; i++) {
                    let listTd = listTr[i].getElementsByTagName("TD");
                    for (let j = fCol; j <= lCol; j++) {
                        let tdTag = listTd[j - 1];
                        tdTag.style.backgroundColor = '#b4d7ff';
                        tdTag.classList.add('highlight');
                        if (window.getSelection) {
                            window.getSelection().removeAllRanges();
                        } else if (document.selection) {
                            document.selection.empty();
                        }
                    }
                }
            }
        }
    };
    td.oncontextmenu = (event) => {
        tempNodeTable = event.target;
        event.preventDefault();
        contextMenu.style.left = event.pageX + 'px';
        contextMenu.style.top = event.pageY + 'px';
        contextMenu.style.display = 'block';
        startFocusOut();
    }
};

let startFocusOut = () => {
    $(document).on("click", function () {
        contextMenu.style.display = 'none';
        $(document).off("click");
    });
};

let addEventToSpan = (span) => {
    span.onclick = (event) => {
        let indexSpan = 0;
        for (let span of selectSizeTableSpanList) {
            indexSpan++;
            if (event.target === span) {
                let carretNode = getCarretNode();
                if (carretNode === null) {
                    carretNode = editor;
                } else {
                    let temp = carretNode;
                    while (true) {
                        if (temp === editor) break;
                        if (temp === document) return;
                        temp = temp.parentNode;
                    }
                }

                let col = Number(document.querySelector('.table-dropdown input[name=col]').value);
                let indexRow = Math.ceil(indexSpan / col);
                let indexCol = indexSpan - (col * (indexRow - 1));

                let table = document.createElement('table');
                table.style.borderCollapse = 'collapse';
                table.style.border = '1px dotted #000';
                table.style.width = '100%';
                table.style.tableLayout = 'fixed';

                let tableBody = document.createElement('tbody');

                let tableHeader = document.createElement('tr');
                tableHeader.style.visibility = 'hidden';
                for (let i = 0; i < indexCol; i++) {
                    let th = document.createElement('th');
                    tableHeader.appendChild(th);
                }
                tableBody.appendChild(tableHeader);

                for (let i = 0; i < indexRow; i++) {
                    let tr = document.createElement('tr');
                    for (let j = 0; j < indexCol; j++) {
                        let td = document.createElement('td');
                        td.style.borderCollapse = 'collapse';
                        td.style.border = '1px dotted #000';
                        td.style.wordWrap = 'break-word';
                        addEventToTd(td);
                        tr.appendChild(td);
                    }
                    tableBody.appendChild(tr);
                }
                table.appendChild(tableBody);

                carretNode.appendChild(table);

                let divTag = document.createElement('div');
                let brTag = document.createElement('br');
                divTag.appendChild(brTag);
                carretNode.appendChild(divTag);

                $(table).colResizable({
                    liveDrag: true,
                    draggingClass: "dragging"
                });

                table.onmouseup = () => {
                    highlightFlag = false;
                }
            }
        }
    };

    span.onmouseover = (event) => {
        for (let span of selectSizeTableSpanList) {
            span.classList.remove('hover');
        }
        let indexSpan = 0;
        for (let span of selectSizeTableSpanList) {
            indexSpan++;
            if (event.target === span) {
                let row = Number(document.querySelector('.table-dropdown input[name=row]').value);
                let col = Number(document.querySelector('.table-dropdown input[name=col]').value);
                let indexRow = Math.ceil(indexSpan / col);
                let indexCol = indexSpan - (col * (indexRow - 1));
                if (row === indexRow && indexRow < 15) {
                    for (let i = 0; i < col; i++) {
                        let s = document.createElement('span');
                        addEventToSpan(s);
                        selectSizeTable.appendChild(s);
                    }
                    document.querySelector('.table-dropdown input[name=row]').value = row + 1;
                    document.getElementById('display-size').innerHTML = indexCol + ' x ' + indexRow;

                    for (let i = 1; i <= indexRow; i++) {
                        for (let j = 1; j <= indexCol; j++) {
                            let index = col * (i - 1) + j;
                            selectSizeTableSpanList[index - 1].classList.add('hover');
                        }
                    }
                    return;
                }
                if (col === indexCol && indexCol < 15) {
                    let cssAtr = 'auto ';
                    for (let i = 0; i < row; i++) {
                        let s = document.createElement('span');
                        addEventToSpan(s);
                        selectSizeTable.appendChild(s);
                    }
                    for (let i = 0; i < col; i++) {
                        cssAtr += 'auto ';
                    }
                    selectSizeTable.style.gridTemplateColumns = cssAtr;
                    document.querySelector('.table-dropdown input[name=col]').value = col + 1;
                    document.getElementById('display-size').innerHTML = indexCol + ' x ' + indexRow;

                    for (let i = 1; i <= indexRow; i++) {
                        for (let j = 1; j <= indexCol; j++) {
                            let index = col * (i - 1) + j;
                            selectSizeTableSpanList[index - 1].classList.add('hover');
                        }
                    }
                    return;
                }
                document.getElementById('display-size').innerHTML = indexCol + ' x ' + indexRow;

                for (let i = 1; i <= indexRow; i++) {
                    for (let j = 1; j <= indexCol; j++) {
                        let index = col * (i - 1) + j;
                        selectSizeTableSpanList[index - 1].classList.add('hover');
                    }
                }
                return;
            }
        }
    };

    span.onmouseout = () => {
        let isHoverSelectSize = false;
        if (selectSizeTable.parentElement.querySelector(':hover') === selectSizeTable) {
            isHoverSelectSize = true;
        }
        if (!isHoverSelectSize) {
            for (let span of selectSizeTableSpanList) {
                span.classList.remove('hover');
            }
            document.getElementById('display-size').innerHTML = 'Insert Table';
        }
    }
};

//main function
editor.contentEditable = true;
editor.spellcheck = false;
document.execCommand('fontSize', false, 4); //set default font size

document.getElementById('select-font').onchange = (event) => {
    let value = event.target.value;
    document.execCommand('fontName', false, value);
};

document.getElementById('select-font-size').onchange = (event) => {
    let value = event.target.value;
    document.execCommand('fontSize', false, value);
};

document.getElementById('bold').onclick = () => {
    document.execCommand('bold', false, null);
    document.getElementById('bold').classList.toggle('button-active');
};

document.getElementById('italic').onclick = () => {
    document.execCommand('italic', false, null);
    document.getElementById('italic').classList.toggle('button-active');
};

document.getElementById('underline').onclick = () => {
    document.execCommand('underline', false, null);
    document.getElementById('underline').classList.toggle('button-active');
};

document.getElementById('align-left').onclick = () => {
    document.execCommand('justifyLeft', false, null);
    document.getElementById('align-left').classList.add('button-active');
    document.getElementById('align-center').classList.remove('button-active');
    document.getElementById('align-right').classList.remove('button-active');
};

document.getElementById('align-center').onclick = () => {
    document.execCommand('justifyCenter', false, null);
    document.getElementById('align-left').classList.remove('button-active');
    document.getElementById('align-center').classList.add('button-active');
    document.getElementById('align-right').classList.remove('button-active');
};

document.getElementById('align-right').onclick = () => {
    document.execCommand('justifyRight', false, null);
    document.getElementById('align-left').classList.remove('button-active');
    document.getElementById('align-center').classList.remove('button-active');
    document.getElementById('align-right').classList.add('button-active');
};

let detectStyleOnCaret = () => {
    let carretNode = getCarretNode();
    highLightButtonStyle(carretNode, false, false, false, false, false, false);
};

let highLightButtonStyle = (node, isChangedFontStyle, isChangedFontSize,
    isBold, isItalic, isUnderline, isAlign) => {
    if (node === null) return;
    if (node === editor) {
        if (!isChangedFontStyle) document.getElementById('select-font').value = 'Times New Roman';
        if (!isChangedFontSize) document.getElementById('select-font-size').value = 4;
        if (!isBold) document.getElementById('bold').classList.remove('button-active');
        if (!isItalic) document.getElementById('italic').classList.remove('button-active');
        if (!isUnderline) document.getElementById('underline').classList.remove('button-active');
        if (!isAlign) {
            document.getElementById('align-left').classList.add('button-active');
            document.getElementById('align-center').classList.remove('button-active');
            document.getElementById('align-right').classList.remove('button-active');
        }
        return;
    }
    let nodeParent = node.parentNode;
    if (node.tagName === 'FONT') {
        if (!isChangedFontStyle) {
            let fontFamily = node.getAttribute('face');
            if (fontFamily !== null) {
                document.getElementById('select-font').value = fontFamily;
                isChangedFontStyle = true;
            }
        }

        if (!isChangedFontSize) {
            let fontSize = node.getAttribute('size');
            if (fontSize !== null) {
                document.getElementById('select-font-size').value = fontSize;
                isChangedFontSize = true;
            }
        }
    }

    if (node.tagName === 'B') {
        if (!isBold) {
            document.getElementById('bold').classList.add('button-active');
            isBold = true;
        }
    }

    if (node.tagName === 'I') {
        if (!isItalic) {
            document.getElementById('italic').classList.add('button-active');
            isItalic = true;
        }
    }

    if (node.tagName === 'U') {
        if (!isUnderline) {
            document.getElementById('underline').classList.add('button-active');
            isUnderline = true;
        }
    }

    if (node.tagName === 'DIV' || node.tagName === 'TD') {
        if (!isAlign) {
            let align = node.style.textAlign;
            if (align === 'left') {
                document.getElementById('align-left').classList.add('button-active');
                document.getElementById('align-center').classList.remove('button-active');
                document.getElementById('align-right').classList.remove('button-active');
                isAlign = true;
            }
            if (align === 'center') {
                document.getElementById('align-left').classList.remove('button-active');
                document.getElementById('align-center').classList.add('button-active');
                document.getElementById('align-right').classList.remove('button-active');
                isAlign = true;
            }
            if (align === 'right') {
                document.getElementById('align-left').classList.remove('button-active');
                document.getElementById('align-center').classList.remove('button-active');
                document.getElementById('align-right').classList.add('button-active');
                isAlign = true;
            }
        }
    }

    highLightButtonStyle(nodeParent, isChangedFontStyle, isChangedFontSize,
        isBold, isItalic, isUnderline, isAlign);
};

editor.onmouseup = detectStyleOnCaret;
editor.onkeyup = detectStyleOnCaret;

for (let li of lineSpaceDropdown.getElementsByTagName('li')) {
    li.onclick = (event) => {
        let liTag;
        let target = event.target;
        if (target.tagName === 'SPAN') {
            let targetTemp = event.target;
            liTag = targetTemp.parentNode;
        } else {
            liTag = event.target;
        }

        let symbols = document.querySelectorAll('#dropdown-content li span:nth-child(2)');
        for (let symbol of symbols) {
            symbol.remove();
        }

        let s = document.createElement('span');
        s.classList.add('icon-check-symbol');
        liTag.appendChild(s);

        let valueSpace = liTag.firstChild.innerHTML;
        let childNodes = editor.childNodes;
        for (let node of childNodes) {
            if (node.style === undefined) continue;
            node.style.lineHeight = valueSpace;
        }
    }
}

document.getElementById('merge-table').onclick = () => {
    let fCol, fRow, lCol, lRow;
    let temp1 = [firstPos[0], lastPos[0], firstPos[0] + firstPos[2] - 1, lastPos[0] + lastPos[2] - 1];
    fCol = Math.min(...temp1);
    lCol = Math.max(...temp1);
    let temp2 = [firstPos[1], lastPos[1], firstPos[1] + firstPos[3] - 1, lastPos[1] + lastPos[3] - 1];
    fRow = Math.min(...temp2);
    lRow = Math.max(...temp2);

    let highlightEl = document.getElementsByClassName('highlight')[0];
    let tBody = highlightEl.parentNode;
    while (true) {
        if (tBody.nodeName === 'TBODY') break;
        tBody = tBody.parentNode;
    }
    let listTrTag = tBody.getElementsByTagName("TR");

    let isFirst = true;
    for (let i = fRow; i <= lRow; i++) {
        let tempTr = listTrTag[i];
        let listTdTag = tempTr.getElementsByTagName("TD");
        if (isFirst) {
            for (let j = fCol - 1; j < lCol; j++) {
                if (isFirst) {
                    resetHighlight(listTdTag[fCol - 1]);
                    listTdTag[fCol - 1].style.backgroundColor = '#b4d7ff';
                    isFirst = false;
                    let colspan = lCol - fCol + 1;
                    let rowspan = lRow - fRow + 1;
                    listTdTag[fCol - 1].setAttribute("colspan", colspan);
                    listTdTag[fCol - 1].setAttribute("rowspan", rowspan);
                    continue;
                }
                listTdTag[j].innerHTML = "";
                listTdTag[j].removeAttribute("class");
                listTdTag[j].removeAttribute("style");
                listTdTag[j].removeAttribute("colspan");
                listTdTag[j].removeAttribute("rowspan");
                listTdTag[j].hidden = true;
            }
            continue;
        }
        for (let j = fCol - 1; j < lCol; j++) {
            listTdTag[j].innerHTML = "";
            listTdTag[j].removeAttribute("class");
            listTdTag[j].removeAttribute("style");
            listTdTag[j].removeAttribute("colspan");
            listTdTag[j].removeAttribute("rowspan");
            listTdTag[j].hidden = true;
        }
    }
};

document.getElementById('insert-row-above').onclick = () => {
    let pos = detectPosition(tempNodeTable);
    let tBody = tempNodeTable.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    let listTrTag = tBody.getElementsByTagName("TR");
    let totalCol = listTrTag[0].getElementsByTagName("TH").length;
    let tr = document.createElement("tr");
    for (let i = 0; i < totalCol; i++) {
        let td = document.createElement("td");
        td.style.borderCollapse = 'collapse';
        td.style.border = '1px dotted #000';
        td.style.wordWrap = 'break-word';
        addEventToTd(td);
        tr.appendChild(td);
    }
    tBody.insertBefore(tr, listTrTag[pos[1]]);

    $(tBody.parentNode).colResizable({
        disable: true
    });
    $(tBody.parentNode).colResizable({
        liveDrag: true,
        draggingClass: "dragging"
    });
};

document.getElementById('insert-row-below').onclick = () => {
    let pos = detectPosition(tempNodeTable);
    let tBody = tempNodeTable.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    let listTrTag = tBody.getElementsByTagName("TR");
    let totalCol = listTrTag[0].getElementsByTagName("TH").length;
    let tr = document.createElement("tr");
    for (let i = 0; i < totalCol; i++) {
        let td = document.createElement("td");
        td.style.borderCollapse = 'collapse';
        td.style.border = '1px dotted #000';
        td.style.wordWrap = 'break-word';
        addEventToTd(td);
        tr.appendChild(td);
    }
    tBody.insertBefore(tr, listTrTag[pos[1] + pos[3]]);

    $(tBody.parentNode).colResizable({
        disable: true
    });
    $(tBody.parentNode).colResizable({
        liveDrag: true,
        draggingClass: "dragging"
    });
};

document.getElementById('insert-col-left').onclick = () => {
    let pos = detectPosition(tempNodeTable);
    let tBody = tempNodeTable.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    let listTrTag = tBody.getElementsByTagName("TR");

    for (let i = 0; i < listTrTag.length; i++) {
        let col;
        if (i === 0) {
            let listThTag = listTrTag[0].getElementsByTagName("TH");
            let th = listThTag[pos[0] - 1];
            let temp = th.style.width;
            let newWidth = Number(temp.substring(0, temp.length - 2)) - 22;
            th.style.width = newWidth + 'px';
            col = document.createElement('th');
            col.style.width = '20px';
            listTrTag[0].insertBefore(col, listThTag[pos[0] - 1]);
            continue;
        }
        let listTdTag = listTrTag[i].getElementsByTagName("TD");
        col = document.createElement('td');
        col.style.borderCollapse = 'collapse';
        col.style.border = '1px dotted #000';
        col.style.wordWrap = 'break-word';
        addEventToTd(col);
        listTrTag[i].insertBefore(col, listTdTag[pos[0] - 1]);
    }

    $(tBody.parentNode).colResizable({
        disable: true
    });
    $(tBody.parentNode).colResizable({
        liveDrag: true,
        draggingClass: "dragging"
    });
};

document.getElementById('insert-col-right').onclick = () => {
    let pos = detectPosition(tempNodeTable);
    pos = [pos[0] + pos[2]];
    let tBody = tempNodeTable.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    let listTrTag = tBody.getElementsByTagName("TR");

    for (let i = 0; i < listTrTag.length; i++) {
        let col;
        if (i === 0) {
            let listThTag = listTrTag[0].getElementsByTagName("TH");
            let th = listThTag[pos[0] - 1];
            let temp = th.style.width;
            let newWidth = Number(temp.substring(0, temp.length - 2)) - 22;
            th.style.width = newWidth + 'px';
            col = document.createElement('th');
            col.style.width = '20px';
            listTrTag[0].insertBefore(col, listThTag[pos[0] - 1]);
            continue;
        }
        let listTdTag = listTrTag[i].getElementsByTagName("TD");
        col = document.createElement('td');
        col.style.borderCollapse = 'collapse';
        col.style.border = '1px dotted #000';
        col.style.wordWrap = 'break-word';
        addEventToTd(col);
        listTrTag[i].insertBefore(col, listTdTag[pos[0] - 1]);
    }

    $(tBody.parentNode).colResizable({
        disable: true
    });
    $(tBody.parentNode).colResizable({
        liveDrag: true,
        draggingClass: "dragging"
    });
};

document.getElementById('delete-row').onclick = () => {
    let pos = detectPosition(tempNodeTable);
    let tBody = tempNodeTable.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    for (let i = 0; i < pos[3]; i++) {
        let listTrTag = tBody.getElementsByTagName('TR');
        let needDelete = listTrTag[pos[1]];
        tBody.removeChild(needDelete);
    }

    $(tBody.parentNode).colResizable({
        disable: true
    });
    $(tBody.parentNode).colResizable({
        liveDrag: true,
        draggingClass: "dragging"
    });
};

document.getElementById('delete-col').onclick = () => {
    let pos = detectPosition(tempNodeTable);
    let tBody = tempNodeTable.parentNode;
    while (true) {
        if (tBody.nodeName === "TBODY") break;
        tBody = tBody.parentNode;
    }
    for (let i = 0; i < pos[2]; i++) {
        let listTrTag = tBody.getElementsByTagName("TR");
        for (let i = 0; i < listTrTag.length; i++) {
            if (i === 0) {
                let listThTag = listTrTag[0].getElementsByTagName("TH");
                let thDelete = listThTag[pos[0] - 1];
                let temp = thDelete.style.width;
                let deleteWidth = Number(temp.substring(0, temp.length - 2));

                let thBefore = listThTag[pos[0] - 2];
                temp = thBefore.style.width;
                let currentWidth = Number(temp.substring(0, temp.length - 2));
                let newWidth = currentWidth + deleteWidth + 1;
                thBefore.style.width = newWidth + 'px';

                listTrTag[0].removeChild(thDelete);
                continue;
            }
            let listTdTag = listTrTag[i].getElementsByTagName("TD");
            listTrTag[i].removeChild(listTdTag[pos[0] - 1]);
        }
    }

    $(tBody.parentNode).colResizable({
        disable: true
    });
    $(tBody.parentNode).colResizable({
        liveDrag: true,
        draggingClass: "dragging"
    });
};