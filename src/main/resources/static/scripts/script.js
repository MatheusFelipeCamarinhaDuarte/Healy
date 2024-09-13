function toggleFragment() {
    var isMedico = document.getElementById("toggleSwitch").checked; // Verifica o estado do switch
    var fragmentContainer = document.getElementById("fragmentContainer");
    var tituloCadastro = document.getElementById("tituloCadastro");

    // Define o tipo de fragmento e atualiza o título
    var tipo = isMedico ? 'medico' : 'paciente';
    tituloCadastro.textContent = 'Cadastro de ' + (isMedico ? 'Médico' : 'Paciente');

    // Faz a requisição ao servidor para trocar o fragmento
    fetch('/cadastro/' + tipo)
        .then(response => response.text())
        .then(html => {
            fragmentContainer.innerHTML = html; // Atualiza o fragmento no container
        });
}