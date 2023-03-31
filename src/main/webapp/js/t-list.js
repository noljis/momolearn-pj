const instructors = [
    {
        name: "Instructor Name",
        designation: "Designation",
        image: "../img/profile/test07.jpg",
        githubLink: ""
    }
];


function drawTable() {
    const container = document.createElement("div");
    container.classList.add("container-xxl", "py-5");

    const innerContainer = document.createElement("div");
    innerContainer.classList.add("container");

    const titleDiv = document.createElement("div");
    titleDiv.classList.add("text-center", "wow", "fadeInUp");
    titleDiv.dataset.wowDelay = "0.1s";

    const title = document.createElement("h4");
    title.classList.add("section-title", "bg-white", "text-center", "text-primary", "px-3");
    title.innerText = "Instructors";

    titleDiv.appendChild(title);
    innerContainer.appendChild(titleDiv);

    const row = document.createElement("div");
    row.classList.add("row", "g-4");

    for (let i = 0; i < 4; i++) {
        const col = document.createElement("div");
        col.classList.add("col-lg-3", "col-md-6", "wow", "fadeInUp");
        col.dataset.wowDelay = "0.1s";

        const teamItem = document.createElement("div");
        teamItem.classList.add("team-item", "bg-light");

        const overflow = document.createElement("div");
        overflow.classList.add("overflow-hidden");

        const img = document.createElement("img");
        img.classList.add("img-fluid");
        img.src = instructors[i].image;
        img.alt = "";

        overflow.appendChild(img);
        teamItem.appendChild(overflow);

        const position = document.createElement("div");
        position.classList.add("position-relative", "d-flex", "justify-content-center");
        position.style.marginTop = "-23px";

        const bgLight = document.createElement("div");
        bgLight.classList.add("bg-light", "d-flex", "justify-content-center", "pt-2", "px-1");

        const githubLink = document.createElement("a");
        githubLink.classList.add("btn", "btn-sm-square", "btn-primary", "mx-1");
        githubLink.href = instructors[i].githubLink;

        const githubIcon = document.createElement("i");
        githubIcon.classList.add("fab", "fa-github");

        githubLink.appendChild(githubIcon);
        bgLight.appendChild(githubLink);
        position.appendChild(bgLight);
        teamItem.appendChild(position);

        const textCenter = document.createElement("div");
        textCenter.classList.add("text-center", "p-4");

        const instructorLink = document.createElement("a");
        instructorLink.href = "../teachers/t-detail.jsp";

        const name = document.createElement("h5");
        name.classList.add("mb-0");
        name.innerText = instructors[i].name;

        instructorLink.appendChild(name);
        textCenter.appendChild(instructorLink);

        const designation = document.createElement("small");
        designation.innerText = instructors[i].designation;

        textCenter.appendChild(designation);
        teamItem.appendChild(textCenter);
        col.appendChild(teamItem);
        row.appendChild(col);
    }

    innerContainer.appendChild(row);
    container.appendChild(innerContainer);
    document.body.appendChild(container);
}


drawTable();



// const createTable = (teachersNo) => {
//     const n = Math.ceil(teachersNo / 4);
//     const table = document.createElement('table');
  
//     for (let i = 0; i < n; i++) {
//       const row = table.insertRow();
  
//       for (let j = 0; j < 4; j++) {
//         const cell = row.insertCell();
  
//         if ((i * 4 + j) < teachersNo) {
//           cell.textContent = `Teacher ${i * 4 + j + 1}`;
//         } else {
//           cell.textContent = '';
//         }
//       }
//     }
  
//     return table;
//   };
  