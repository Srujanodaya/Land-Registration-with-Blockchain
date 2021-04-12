import React, {Component} from 'react'
import LandContract from "./artifacts/Land.json"
import getWeb3 from "./getWeb3"

import { Button, Spinner} from 'react-bootstrap'
var verified;
class RegisterSeller extends Component {
    constructor(props){
        super(props)

        this.state = {
            LandInstance: undefined,
            account: null,
            web3: null,
            flag: null, 
            verified: '',
            registered: '',
        }
    }

    componentDidMount = async () => {
        //For refreshing page only once
        if(!window.location.hash){
            window.location = window.location + '#loaded';
            window.location.reload();
        }

        try{
            //Get network provider and web3 instance
            const web3 = await getWeb3();

            const accounts = await web3.eth.getAccounts();
            
            const networkId = await web3.eth.net.getId();
            const deployedNetwork = LandContract.networks[networkId];
            const instance = new web3.eth.Contract(
                LandContract.abi,
                deployedNetwork && deployedNetwork.address,
            );
            
            const currentAddress = await web3.currentProvider.selectedAddress;
            console.log(currentAddress);
            this.setState({ LandInstance: instance, web3: web3, account: accounts[0] });
            verified = await this.state.LandInstance.methods.isVerified(currentAddress).call();
            console.log(verified);
            this.setState({verified: verified});
            var registered = await this.state.LandInstance.methods.isSeller(currentAddress).call();
            console.log(registered);
            this.setState({registered: registered});
            // verified = verified.toString();
            // console.log(verified);
            
        }catch (error) {
            // Catch any errors for any of the above operations.
            alert(
              `Failed to load web3, accounts, or contract. Check console for details.`,
            );
            console.error(error);
          }
    };

    render() {
        if (!this.state.web3) {
          return (
            <div>
              <div>
                <h1>
                <Spinner animation="border" variant="warning" />
                </h1>
              </div>
              
            </div>
          );
        }

        if (!this.state.registered) {
          return (
            <div>
              <div>
                <h1>
                You are not authorized to view this page.
                </h1>
              </div>
              
            </div>
          );
        }
        
        return (
            <div className="App">  
              <div>
                <div>
                  <h1>
                    Seller Dashboard
                  </h1>
                </div>
              </div>
                
              <div className="form">
                  <Button href="/AddLand" disabled={!this.state.verified} className="button-vote">
                      Add Land
                  </Button>
              </div>
              
            </div>
        );
        
    }
}

export default RegisterSeller;