let lecture;

function drawTable(list) {
	const container = document.createElement("div");
	container.className = "container";

	let searchTitle = document.querySelector('#searchLecture').value;
	const title = document.createElement("h2");
	title.className = "text-center mt-5 mb-3";
	title.textContent = `⌨️ \"${searchTitle}\" 검색 결과`;

	const row = document.createElement("div");
	row.className = "row justify-content-center";

	list.forEach((lecture) => {
		const col = document.createElement("div");
		col.className = "col-md-3";

		const card = document.createElement("div");
		card.className = "card";
		card.style.border = "2px solid #78d5e7";
		card.style.borderRadius = "5px";
		card.style.padding = "10px";
		card.style.marginBottom = "30px";
		card.style.textAlign = "center";
		card.style.height = "410px";
		card.style.backgroundColor = "#E8F5FF";

		const thumbnailLink = document.createElement("a");
		thumbnailLink.href = `/lecture/detail/${lecture.id}`;

		const thumbnail = document.createElement("img");
		thumbnail.src = `../../momolearn/img/lecture/${lecture.image}`;
		thumbnail.alt = "Lecture Thumbnail";
		thumbnail.style.maxWidth = "100%";
		thumbnail.style.maxHeight = "210px";
		thumbnail.style.border = "1px solid #ddd";
		thumbnail.style.objectFit = "contain";
		thumbnail.style.borderRadius = "5px";

		thumbnailLink.appendChild(thumbnail);

		const name = document.createElement("h5");
		name.textContent = lecture.title;

		const description = document.createElement("p");
		description.textContent = lecture.info;

		const teacher = document.createElement("p");
		teacher.textContent = `강사명: ${lecture.teacher}`;
		teacher.style.fontWeight = "bold";
		teacher.style.borderRadius = "5px";

		const price = document.createElement("p");
		price.style.fontWeight = "bold";
		price.className = "text-primary";
		price.textContent = `${lecture.price}￦`;

		const totalAndStudents = document.createElement("div");
		totalAndStudents.style.display = "flex";
		totalAndStudents.style.justifyContent = "center";
		totalAndStudents.style.border = "1px solid black";

		const totalWrapper = document.createElement("div");
		totalWrapper.style.width = "50%";
		totalWrapper.style.textAlign = "center";
		const total = document.createElement("p");
		total.style.display = "inline-block";
		total.style.margin = 0;
		total.style.fontSize = "smaller";
		total.textContent = `강좌 수: ${lecture.cnt}개`;
		totalWrapper.appendChild(total);

		const studentsWrapper = document.createElement("div");
		studentsWrapper.style.width = "50%";
		studentsWrapper.style.textAlign = "center";
		const students = document.createElement("p");
		students.style.display = "inline-block";
		students.style.margin = 0;
		students.style.fontSize = "smaller";
		students.textContent = `수강 학생 수: ${lecture.applyCnt}명`;
		studentsWrapper.appendChild(students);


		const categoryWrapper = document.createElement("div");
		categoryWrapper.className = "d-flex flex-wrap justify-content-center mb-2";
		categoryWrapper.style.marginTop = "10px";

		const categories = JSON.parse(lecture.category);
		categories.forEach((category) => {
			const categoryBtn = document.createElement("a");
			categoryBtn.className = "btn btn-sm btn-primary px-3 me-2 mb-2";
			categoryBtn.style.borderRadius = "30px";
			categoryBtn.textContent = category;
			categoryWrapper.appendChild(categoryBtn);
		});

		card.appendChild(thumbnailLink);
		card.appendChild(categoryWrapper);
		card.appendChild(name);
		card.appendChild(description);
		card.appendChild(teacher);
		card.appendChild(price);
		totalAndStudents.appendChild(totalWrapper);
		totalAndStudents.appendChild(studentsWrapper);
		card.appendChild(totalAndStudents);

		col.appendChild(card);
		row.appendChild(col);
	});

	container.appendChild(title);
	container.appendChild(row);
	document.getElementById("searchList").innerHTML = '';


	return document.getElementById("searchList").appendChild(container);
}


// 강의 검색
function dataReceive(title) {
	axios({
		method: "GET",
		url: "../../momolearn/lectures/searchLecture/" + title
	}).then(function(resData) {
		lecture = resData.data;
		console.log('넘어온 데이터' + lecture);
		// data타입이 object가 아니면 json이 아닌 예외 메세지가 왔다는 뜻
		if (typeof (lecture) == "string") {
			alert("실행중 문제 발생 : " + lecture);
		} else if (typeof (lecture) == "object") {
			drawTable(lecture);
		}
	}).catch(function() {
		alert("실행중 문제 발생 : " + "값을 입력해주세요.");
	});
}
//id=btn인 버튼을 클릭시 발생
document.querySelector('#btn').addEventListener('click', function() {
	// title: searchLecture input 태그의 값
	let title = document.querySelector('#searchLecture').value;
	console.log(title);
	dataReceive(title);
	title.innerText = '';
});
