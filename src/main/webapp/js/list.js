let lecture2;

function drawTable2(list) {
	
	let container = document.createElement("div");
	container.className = "container";

	let row = document.createElement("div");
	row.className = "row justify-content-center";

	list.forEach((lecture) => {
		let col = document.createElement("div");
		col.className = "col-md-3";

		let card = document.createElement("div");
		card.className = "card shadow";
		card.style.border = "2px solid #78d5e7";
		card.style.borderRadius = "5px";
		card.style.padding = "10px";
		card.style.marginBottom = "30px";
		card.style.textAlign = "center";
		card.style.height = "410px";

		let thumbnailLink = document.createElement("a");
		thumbnailLink.href = `/../../momolearn/lectures/detail/${lecture.id}`;

		let thumbnail = document.createElement("img");
		thumbnail.src = `/../../momolearn/img/lecture/${lecture.image}`;
		thumbnail.alt = "Lecture Thumbnail";
		thumbnail.style.maxWidth = "100%";
		thumbnail.style.maxHeight = "210px";
		thumbnail.style.border = "1px solid #ddd";
		thumbnail.style.objectFit = "contain";
		thumbnail.style.borderRadius = "5px";

		thumbnailLink.appendChild(thumbnail);

		const name = document.createElement("h5");
		name.textContent = lecture.title;
		name.style.cursor = "pointer";
		name.setAttribute('onclick', `location.href="/../../momolearn/lectures/detail/${lecture.id}"`);

		let description = document.createElement("p");
		description.textContent = lecture.info;

		const teacher = document.createElement("p");
		teacher.textContent = `👩‍🏫 ${lecture.teacher}`;
		teacher.style.fontWeight = "bold";
		teacher.style.borderRadius = "5px";

		const price = document.createElement("p");
		price.style.fontWeight = "bold";
		price.className = "text-primary";
		price.textContent = `${lecture.price}￦`;

		const totalAndStudents = document.createElement("div");
		totalAndStudents.style.display = "flex";
		totalAndStudents.style.justifyContent = "center";
		totalAndStudents.style.border = "2px solid #ddd";
		totalAndStudents.style.backgroundColor = "#EBF5FF";
		totalAndStudents.style.borderRadius = "10px";
		totalAndStudents.style.padding = "5px 10px";

		const totalWrapper = document.createElement("div");
		totalWrapper.style.width = "50%";
		totalWrapper.style.textAlign = "center";
		const total = document.createElement("p");
		total.style.display = "inline-block";
		total.style.margin = 0;
		total.style.fontSize = "smaller";
		total.style.fontWeight = "bold";
		total.style.color = "#666";
		total.textContent = `📚강좌 수: ${lecture.cnt}개`;
		totalWrapper.appendChild(total);

		const studentsWrapper = document.createElement("div");
		studentsWrapper.style.width = "50%";
		studentsWrapper.style.textAlign = "center";
		const students = document.createElement("p");
		students.style.display = "inline-block";
		students.style.margin = 0;
		students.style.fontSize = "smaller";
		students.style.fontWeight = "bold";
		students.style.color = "#666";
		students.textContent = `🧑‍💻수강생: ${lecture.applyCnt}명`;
		studentsWrapper.appendChild(students);


		let categoryWrapper = document.createElement("div");
		categoryWrapper.className = "d-flex flex-wrap justify-content-center mb-2";
		categoryWrapper.style.marginTop = "10px";

		let categories = JSON.parse(lecture.category);

		categories.forEach((category) => {
			
			let categoryBtn = document.createElement("a");
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

	container.appendChild(row);
	document.getElementById("lectureList").innerHTML = '';


	return document.getElementById("lectureList").appendChild(container);
}

window.onload = function() {
	
	axios({
		method: "GET",
		url: "/../../momolearn/lectures/lecture-list"
	}).then(function(resData) {
		lecture2 = resData.data;
		
		if (typeof (lecture2) == "string") {
			alert("실행중 문제 발생 : " + lecture2);
		} else if (typeof (lecture2) == "object") {
			drawTable2(lecture2);
		}
		
	}).catch(function() {
		alert("실행중 문제 발생 : " + "검색된 강의가 없습니다.");
	});
}

function dataReceive2(title) {
	
	axios({
		method: "GET",
		url: "/../../momolearn/lectures/search-category/" + title
	}).then(function(resData) {
		lecture2 = resData.data;
		
		if (typeof (lecture2) == "string") {
			alert(lecture2);
			return document.getElementById("lectureList").innerHTML = '';
		} else if (typeof (lecture2) == "object") {
			drawTable2(lecture2);
		}
		
	}).catch(function() {
		alert("실행중 문제 발생 : " + "값을 입력해주세요.");
	});
}

document.querySelectorAll('#catebtn').forEach(function(button) {
	
	button.addEventListener('click', function() {
		
		let title = button.value;
		dataReceive2(title);
		title.innerText = '';
		
	});
});

document.querySelector('#catebtn2').addEventListener('click', function() {
	
	axios({
		method: "GET",
		url: "/../../momolearn/lectures/lecture-list"
	}).then(function(resData) {
		lecture2 = resData.data;
		
		if (typeof (lecture2) == "string") {
			alert("실행중 문제 발생 : " + lecture2);
		} else if (typeof (lecture2) == "object") {
			drawTable2(lecture2);
		}
		
	}).catch(function() {
		alert("실행중 문제 발생 : " + "검색된 강의가 없습니다.");
	});
});
