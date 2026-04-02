import { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [data, setData] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/dashboard")
      .then((res) => setData(res.data))
      .catch((err) => console.error(err));
  }, []);

  if (!data) {
    return <h2 style={{ textAlign: "center", marginTop: "40px" }}>Carregando...</h2>;
  }

  return (
    <div className="container">
      <h1 className="titulo">Dashboard LogiTrack</h1>

      <div className="cards">
        <div className="card">
          <h3>Total KM</h3>
          <p>{data.total_km}</p>
        </div>

        <div className="card">
          <h3>Projeção Financeira</h3>
          <p>R$ {data.projecao_financeira}</p>
        </div>
      </div>

      <div className="bloco">
        <h2>Ranking de Utilização</h2>
        <div className="card">
          <p><strong>Modelo:</strong> {data.ranking_utilizacao.modelo}</p>
          <p><strong>Placa:</strong> {data.ranking_utilizacao.placa}</p>
          <p><strong>Tipo:</strong> {data.ranking_utilizacao.tipo}</p>
          <p><strong>Total KM:</strong> {data.ranking_utilizacao.total_km}</p>
        </div>
      </div>

      <div className="bloco">
        <h2>Volume por Categoria</h2>
        <div className="cards">
          {data.volume_por_categoria.map((item, index) => (
            <div className="card" key={index}>
              <p><strong>{item.categoria}</strong></p>
              <p>{item.quantidade} viagens</p>
            </div>
          ))}
        </div>
      </div>

      <div className="bloco">
        <h2>Próximas Manutenções</h2>
        <table>
          <thead>
            <tr>
              <th>Modelo</th>
              <th>Placa</th>
              <th>Serviço</th>
              <th>Status</th>
              <th>Custo</th>
            </tr>
          </thead>
          <tbody>
            {data.proximas_manutencoes.map((m) => (
              <tr key={m.id}>
                <td>{m.modelo}</td>
                <td>{m.placa}</td>
                <td>{m.tipo_servico}</td>
                <td>{m.status}</td>
                <td>R$ {m.custo_estimado}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;