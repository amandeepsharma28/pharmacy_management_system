import React from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "../stock/StockList.css";
import { Helmet } from "react-helmet";
import Footer from "../common/Footer"; //refer to Footer.js
import Header from "../common/Header";
import NavPharmacist from "./NavPharmacist";
import {Link } from "react-router-dom";

class Report extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            report_data:[],
            isLoaded:false,
            startDate:'',
            message:"",
            sales_count: 0,
            purchase_count:0,
            endDate:'',
            date:new Date()
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSalesSubmit=this.handleSalesSubmit.bind(this);
        this.handleStartChange=this.handleStartChange.bind(this);
        this.handleEndChange=this.handleEndChange.bind(this);
        this.handleReset=this.handleReset.bind(this);
    }
    handleReset=(event)=>{
        this.setState({report_data:[],
            isLoaded:false,
            startDate:'',
            message:"",
            sales_count: 0,
            endDate:'',
            date:new Date()});
    }
//--------------------------------------------------FOR SALES REPORT-----------------------------------------------------------
    handleSalesSubmit=(event)=>{
        event.preventDefault();
        const url= 'http://localhost:8762/sales-managment-service/order/report?from=';   


        fetch(url+this.state.startDate+'&to='+this.state.endDate,{
        method: "GET"}).then(
            response => {
                if (response.ok) {     
                    response.json().then(json_response => {
                        console.log(json_response);
                        if(json_response.length!==0)
                        {
                        this.setState({
                            report_data: json_response,
                            sales_count: json_response.length, 
                            message:"Sales Total: $",
                            report_index: 0, 
                            isLoaded: true,
                            error: null
                        });
                        }
                        else{
                            this.setState({
                                message:"No Sales!!!!!", 
                                error: null,
                                sales_count:0
                            });
                        }
            });
        } 
        else {
            response.json().then(json_response => {
                this.setState({
                    isLoaded: false,
                    report_data: {}, 
                    sales_count:0,
                    report_index: 0,
                    message:"Enter a valid Peroid"
                });
            });
        }
    },

    error => {
        this.setState({
            isLoaded: false,
            error: {
                message:
                    "AJAX error, URL wrong or unreachable, see console"
            }, 
            message:error.message,
            report_data: {}, 
            sales_count: 0,
            report_index: 0
        });
    });
}
    handleChange(date) {
        this.setState({
            date: date
        });
        }
  //   onChange = date => this.setState({ date })
  handleStartChange=(event)=> {
    this.setState({startDate: event.target.value});
 }
 handleEndChange=(event)=> {
    this.setState({endDate: event.target.value});
 }
    
 
componentDidUpdate(){
    
}
    render() {
        const rows=[];
        const h2=[];
        if(this.state.error){
            h2.push(<h2>{this.state.message}</h2>);
        }
        else if(this.state.sales_count===0)
        {
            h2.push(<h2>{this.state.message}</h2>);
        
        }
        else if(this.state.sales_count!==0)
        {    
            var total=0;
            for(let i=0;i<this.state.sales_count;i++)
            {
                rows.push(
                    <tr key={this.state.report_data[i].soId} align="start">
                        <td className="cell100 column1"  >{this.state.report_data[i].soId}</td>
                        <td className="cell100 column2" >{this.state.report_data[i].customerName}</td>
                        <td className="cell100 column3" >{this.state.report_data[i].total}</td>
                    </tr>
                )
                total+=this.state.report_data[i].total;
            }
            
        h2.push(<h2>{this.state.message}{total}</h2>);
        }
        else{
            h2.push(<h2>{this.state.message}</h2>);
        }
    return (
<div className="Parent_error_class">
    <Header/>
    <NavPharmacist/>
                <div className="application">
                    <div>
                        <Helmet>
                            <title>Product List</title>
                            <meta charset="UTF-8" />
                            <meta name="viewport" content="width=device-width, initial-scale=1" />
                            <link rel="icon" type="image/png" href='/public/images/icons/favicon.ico' />
                            <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css" />
                            <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
                            <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css" />
                            <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css" />
                            <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css" />
                            <link rel="stylesheet" type="text/css" href="css/util.css" />
                            <link rel="stylesheet" type="text/css" href="css/main.css" />
                        </Helmet>
                    </div>
                    <br></br><br></br><br></br>
                <h2>SALES REPORT</h2>
                <br></br>
                <form className="form-group" style={{ margin: "auto", maxWidth: "520px" }}>
                        <label htmlFor="from"><b>From: </b></label >
                        <input type="date" className="report_date" id="from" name="from" defaultValue={this.state.date} onChange={this.handleStartChange}/>&nbsp;
                        &nbsp;&nbsp;&nbsp;<label htmlFor="to"><b>To: </b></label>
                        <input type="date" className="report_date" id="to" name="from" defaultValue={this.state.date} onChange={this.handleEndChange}/>
                        <br/><br/>
                        <button className="report_button" type="submit" onClick={this.handleSalesSubmit}>Sales Report</button>
                        <button className="report_button" type="reset" onClick={this.handleReset}>Reset</button>
                        <br></br>
                        {h2}
                </form>
                    <div class="limiter">
                        <div class="container-table100">
                        <div class="wrap-table100">
                        <div class="table100 ver2 m-b-110"> 
                        <div className="table100-head">
                                    <table>
                                        <thead>
                                            <tr className="row100 head">
                                                <th className="cell100 column1" >Order Id</th>
                                                <th className="cell100 column2">Customer Name</th>
                                                <th className="cell100 column4">Order Total</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>

                                <div className="table100-body js-pscroll">
                                    <table>
                                        <tbody>
                                            {rows}
                                        </tbody>
                                    </table>
                                </div>
                   
                <br></br><br></br><br></br>
                </div>
                </div>
                </div>
                </div>
                </div>
                <Footer/>
            </div>
        );
    }
}
export default Report;


