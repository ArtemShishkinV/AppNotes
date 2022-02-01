function showLogin() {
    $("#profile").hide();
    $("#logout").hide();
}

function showProfile() {
    $("#login").hide();
    $("#register").hide();
    $("#profile").show();
    $("#logout").show();
}

function addTag(noteId) {
    var params = document.getElementById("format");
    var tag = params.options[params.selectedIndex].value;

    console.log("123");

    $.post("addNoteTag", {id: noteId, tag_id: tag}, function (returnedData) {
        console.log(returnedData);
    }).fail(function () {
        console.log("error");
    });

    $('#tags').load(window.location.href + " #tags");
}

function removeTag(noteId, tagId) {
    $.post("deleteNoteTag", {id: noteId, tag_id: tagId}, function (returnedData) {
        console.log(returnedData);
    }).fail(function () {
        console.log("error");
    });

    $('#tags').load(window.location.href + " #tags");
}

function refreshPage() {
    $(document).ready(function () {
        setInterval(function () {
            $('#list-users').load(window.location.href + " #list-users");
        }, 1000);
    });
}