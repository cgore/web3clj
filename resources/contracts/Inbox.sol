// compiler version pinning
pragma solidity ^0.4.17;

// define a new contract
contract Inbox {
    // storage variable which is stored on the blockchain
    string public message;

    // constructor, invoked when we deploy contract to blockchain
    function Inbox(string newMessage) public {
        message = newMessage;
    }

    function setMessage(string newMessage) public {
        message = newMessage;
    }

    // function-name() function-type return-type
    function getMessage() public view returns (string) {
        return message;
    }
}