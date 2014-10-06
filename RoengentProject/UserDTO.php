<?php
namespace testSpaceStuff;

class UserDTO {
	

	private $name;
	private $userID;
	private $password;
	private $phoneNo;
	private $department;
	private $permissions; // kan muligvis lave vores egen enum klasse - Rùni

	
	function __construct($name="", $userID="", $password="", $phoneNo="", $department="", $permissions=array()){
		$this->username = $name;
		$this->userID = $userID;
		$this->password= $password;
		$this->phoneNo=$phoneNo;
		$this->department=$department;
		$this->permissions = $permissions;
	}
	
	function getUserId(){
		return $this->userId;
	}
	function setUserId($userId){
		$this->userId = $userId;
	}
	
	
	function getName(){
		return $this->name;
	}
	function setName($name){
		$this->name = $name;
	}
	
	
	function getPermissions(){
		return $this->permissions;
	}
	function setPermissions($permissions=array()){
		$this->permissions = $permissions;
	}
	
	function getPassword(){
		return $this->password;
	}
	
	function setPassword($password){
		$this->password=$password;
	}
	
	function getPhoneNo(){
		return $this->phoneNo;
	}
	
	function  setPhoneNo($phoneNo){
		$this->phoneNo=$phoneNo;
	}
	
	function getDepartment(){
		return $this->department;
	}
	
	function setDepartment($department){
		$this->department=$department;
	}
	
	
}
