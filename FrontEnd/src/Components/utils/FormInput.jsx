import "./FormInput.css";

const FormInput = ({ label, type, name, value, onChange, errors }) => {
  return (
    <div className="form-group">
      <label>{label}:</label>
      <input type={type} name={name} value={value} onChange={onChange} />
      {errors && <span className="error">{errors}</span>}
    </div>
  );
};

export default FormInput;
