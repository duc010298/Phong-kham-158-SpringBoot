let listJumbotronTag = document.getElementsByClassName("jumbotron-custom-home");

for (let t of listJumbotronTag) {
    t.onclick = (event) => {
        let aTag = event.target.getElementsByTagName("a")[0];
        aTag.click();
    }
}