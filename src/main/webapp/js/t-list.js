function createCard(teacher) {
    const card = document.createElement('div');

    card.classList.add('card');
    card.style.width = '300px';
    card.style.flex = '1 1 1';
    card.style.border = '2px solid var(--primary)';
    card.style.borderRadius = '10px';
    card.style.margin = '20px';

    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    // const cardImg = document.createElement('img');
    // cardImg.classList.add('card-img-bottom');
    // cardImg.src = '../img/about.jpg';
    // cardImg.alt = 'Card image';
    // cardImg.style.width = '100%';

    const cardTitle = document.createElement('h4');
    cardTitle.classList.add('card-title');
    cardTitle.textContent = `강사 ${teacher.teacherNo}`;
    cardTitle.style.borderBottom = '2px solid var(--primary)';

    const cardText = document.createElement('p');
    cardText.classList.add('card-text');
    cardText.textContent = `분야: ${teacher.hope}`;
    cardText.style.border = 'none';

    const cardLink = document.createElement('a');
    cardLink.classList.add('btn', 'btn-primary');
    //cardLink.href = `/../../momolearn/teachers/list/${parseInt(teacher.teacherNo)}`;
    cardLink.href = `javascript:detailView(${parseInt(teacher.teacherNo)})`;


    cardLink.textContent = '프로필 상세보기';
    cardLink.style.borderTop = '2px solid var(--primary)';


    // cardBody.appendChild(cardImg);
    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardText);
    cardBody.appendChild(cardLink);
    card.appendChild(cardBody);

    return card;
}

window.onload = function () {
    axios({
        method: "GET",
        url: "/../../momolearn/teachers/t-list"
    })
        .then(function (response) {
            const container = document.getElementById('teacher-list');
            for (let i = 0; i < response.data.length; i++) {
                const teacher = response.data[i];
                console.log(response.data[i]);
                const card = createCard(teacher);
                container.appendChild(card);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

//강사 프로필 상세보기 처리 
function detailView(teacherNo) {
    axios({
        method: "GET",
        url: `/../../momolearn/teachers/list/${teacherNo}`
    })
        .then(function (response) {
            console.log(response.data);

            document.getElementById('id01').style.display = 'block';
            document.getElementById('imgView').src = "../img/profile/" + response.data[0].profile;
            document.getElementById('name').style.display = 'block';
            document.getElementById('name').innerHTML = response.data[0].name;
            document.getElementById('name').style.borderBottom = '2px solid #06BBCC';
            console.log(response.data[0].name);
            document.getElementById('hope').style.display = 'block';
            document.getElementById('hope').innerHTML = `<b>분야: </b>` + response.data[0].hope;
            document.getElementById('pfLink').style.display = 'block';
            document.getElementById('pfLink').innerHTML = response.data[0].pfLink;
            document.getElementById('intro').style.display = 'block';
            document.getElementById('intro').innerHTML = response.data[0].intro;

        })
        .catch(function (error) {
            console.log(error);
        });
}