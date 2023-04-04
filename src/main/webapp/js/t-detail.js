function showProfile(data) {
    const { name, hope, intro, pfLink, profile } = data;

    document.getElementById('profile-name').textContent = name;
    document.getElementById('profile-img').style.backgroundImage = `url('/../../momolearn/teachers/t-list/img/${profile}')`;
    document.getElementById('profile-hope').textContent = hope;
    document.getElementById('profile-intro').textContent = intro;
    document.getElementById('profile-pfLink').href = pfLink;

    document.getElementById('profile-container').style.display = 'block';
}

function getTeacherData(teacherNo) {
    return axios({
        method: 'GET',
        url: `/../../momolearn/teachers/t-list/${teacherNo}`
    })
        .then(function (response) {
            return response.data;
        })
        .catch(function (error) {
            console.log(error);
        });
}


getTeacherData(teacherNo).then(function (data) {
    showProfile(data);
});
