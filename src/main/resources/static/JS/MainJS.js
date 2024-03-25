document.addEventListener('DOMContentLoaded', function () {
    const flechaDireita = document.getElementById("flechaDireita");
    const flechaEsquerda = document.getElementById("flechaEsquerda");
    const aberto = document.querySelector(".aberto");

    if (flechaDireita && flechaEsquerda && aberto) {
        flechaDireita.addEventListener('click', () => {
            aberto.style.display = "block";
        });

        flechaEsquerda.addEventListener('click', () => {
            aberto.style.display = "none";
        });
    } else {
        console.error("Elementos flechaDireita, flechaEsquerda ou aberto não encontrados!");
    }
});


