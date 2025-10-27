function hola() {
            alert("Hola!"); // aparece una alerta en vez de en la consola
            console.log("Hola!");
            var script = document.createElement('script');
            script.src = 'path/to/script.js';
            document.body.appendChild(script);
        }
	document.getElementById("clear").addEventListener("click", clearOutput, false);

function onboyIsloaded() {
	const div = document.createElement('div');
	div.textContent = "New Element";
	div.style.color = 'red';
	document.querySelector('h1').appendChild(div);
}



function testVariables() {
	let output = '';
	let a = 10;
	const b = 'Hello';
	var c = true;
	output += "let a = ${a}<br>";
	output += 'const b = ${b}<br>';
	output += `var c = ${c}<br>`;
	showOutput(output);
}

function testFunctions() {
	let output = '';
	function add(x, y) { return x + y; }
	const multiply = (x, y) => x * y;
	output += `add(2, 3) = ${add(2, 3)}<br>`;
	output += `multiply(4, 5) = ${multiply(4, 5)}<br>`;
	showOutput(output);
}

function testArrays() {
	let arr = [1, 2, 3, 4, 5];
	let output = '';
	output += `Array: [${arr.join(', ')}]<br>`;
	output += `First element: ${arr[0]}<br>`;
	output += `Length: ${arr.length}<br>`;
	output += `Mapped (x2): [${arr.map(x => x * 2).join(', ')}]<br>`;
	showOutput(output);
}

function testObjects() {
	let person = { name: 'Alice', age: 30 };
	let output = '';
	output += `Name: ${person.name}<br>`;
	output += `Age: ${person.age}<br>`;
	person.job = 'Engineer';
	output += `Job: ${person.job}<br>`;
	showOutput(output);
}

function clearOutput() {
//    document.querySelector('#output').innerHTML = '';
	document.getElementById('output').innerHTML = '';
}

function showOutput(html) {
	document.getElementById('output').innerHTML = html;
    //document.getElementById('titulo').textContent = 'Nuevo Título';
    //document.getElementById('titulo').style.color = 'blue';
}

document.getElementById('buttonAlert').addEventListener('click', function() {
    alert('Button clicked!');
    console.log('Alert button button was clicked.');
}, true);

document.getElementById('divAlert').addEventListener('click', function() {
    alert('Div clicked!');
    console.log('DivAlert was clicked.');
}, true);

document.getElementById('divAlert').removeEventListener('click');

let signupForm = document.querySelector('[name="signup"]');
console.log(signupForm);
signupForm = document.querySelector('.container form');
console.log(signupForm);
signupForm = document.querySelector('#nameForm');
console.log(signupForm);

signupForm.addEventListener("submit", function(event) {
	const nameInput = document.getElementById('name');
	const name = nameInput.value.trim();
	//name lenght must be greater than 3
	if (name.length < 3) {
		alert('Name must be at least 3 characters long.');
		event.preventDefault(); // Prevent form submission
		window.location.href = 'www.github.com';
		return false;
	}
});