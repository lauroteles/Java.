import React from "react";

export default (props) => {

    const options = props.lista ? props.lista.map(option => {
        return (
            <option value={option.value} >{option.label}</option>
        )
    }): [];
    return(
        <select {...props}>
            {options}
        </select>

    )

}
