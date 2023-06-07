import React, { useState,useEffect  } from "react";
// import { useFetch } from "../useFetch";
import Select from "react-select";
import { useForm } from "react-hook-form";
import "./Supplier.css";

const suppliers = [
  { label: "Cedula", value: "C" },
  { label: "RUC", value: "R" },
];
const cuenta = [
    { label: "AHORROS", value: "1" },
    { label: "CORRIENTE", value: "2" },
  ];

export const Suppliers = () => {
  
const handleSelectChange = (event) => {
    console.log(event);
  };

  const {
    register,
    formState: { errors },
    watch,
  } = useForm({});
  
  const onSubmit = (data) => {
    console.log(data);
  };


//GET API INSTITUCION
const [data, setData] = useState([]);
  useEffect(() => {
    fetchData();
  }, []);
  const fetchData = async () => {
    try {
      const response = await fetch('https://desarrollo.gti.fin.ec/boton-web-api-ws-1.0/coopagos/web/public/institution');
      const jsonData = await response.json();
      setData(jsonData);
    } catch (error) {
      console.log('Error fetching data:', error);
    }
  };

  const [institution, setInstitucion] = useState('');
  const [identificationType, setTipoIdentificacion] = useState('');
  const [identification, setIdentificacion] = useState('');
  const [accountType , setTipoCuenta]= useState('');
  const [account, setnCuenta] = useState('');
  const [amount, setValor] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = {
      institution: institution,
      identificationType:identificationType,
      identification:identification,
      accountType:accountType,
      account:account,
      amount:amount
    };

    fetch('http://localhost:8090/api/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.json())
      .then((responseData) => {
        console.log(responseData);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  return (
    <div className=" Suppliers-container ">
      <h1>Transaccion</h1>
      <form>
        <div>
          <h3> Institucion:</h3>
          
          <Select
            options={data.map((sup) => ({
              label: sup.nombre,
              value: sup.codigoInstitucion,
            }))}
            onChange={handleSelectChange}
          />
        </div>
        <br></br>
        <div>
          <h3> Selecione el tipo de identificacion :</h3>
          <Select
            // defaultValue={suppliers[0]}
            options={suppliers}
            onChange={handleSelectChange}
          />
        </div>
        <br></br>
        <div>
          <label>Identificacion: </label>
          <input
            type="text"
            {...register("cedula", {
              required: true,
              minLength: 10,
            })}
          />
          {errors.cedula?.type === "required" && (
            <p>El campo identificacion es requerido</p>
          )}
          {errors.cedula?.type === "minLength" && (
            <p>El campo identificacion debe tener menos de 10 caracteres</p>
          )}
        </div>
        <br></br>
        <div>
          <h3> Selecione el tipo de cuenta :</h3>

          <Select
            // defaultValue={cuenta[0]}
            options={cuenta}
            onChange={handleSelectChange}
          />
        </div>
        <br></br>
        <div>
          <label>Numero de cuenta: </label>
          <input
            type="text"
            {...register("nCuenta", {
              required: true,
              minLength: 10,
            })}
          />
          {errors.nCuenta?.type === "required" && (
            <p>El campo identificacion es requerido</p>
          )}
          {errors.nCuenta?.type === "minLength" && (
            <p>El campo identificacion debe tener menos de 10 caracteres</p>
          )}
        </div>
        <br></br>
        <div>
          <label>Valor a transferir: </label>
          <input type="number" />
        </div>
        <input type="submit" value="Enviar" />
      </form>
    </div>
  );
};
