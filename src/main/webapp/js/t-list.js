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

    const cardImg = document.createElement('img');
    cardImg.classList.add('card-img-bottom');
    cardImg.src = '../img/teacher-1.jpg';
    cardImg.alt = 'Card image';
    cardImg.style.width = '100%';

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
    cardLink.href = `/../../momolearn/teachers/t-list/${teacher.teacherNo}`;
    cardLink.textContent = '프로필 상세보기';
    cardLink.style.borderTop = '2px solid var(--primary)';

    cardBody.appendChild(cardImg);
    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardText);
    cardBody.appendChild(cardLink);
    card.appendChild(cardBody);

    return card;
}

window.onload = function () {
    axios({
        method: 'GET',
        url: '/../../momolearn/teachers/t-list'
    })
        .then(function (response) {
            response.data.forEach(function (teacher) {
                const card = createCard(teacher);
                const container = document.getElementById('teacher-list');
                container.appendChild(card);
            });
        })
        .catch(function (error) {
            console.log(error);
        });
}
