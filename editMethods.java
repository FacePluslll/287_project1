public void setName(String newName) {
	this.name = newName;
}
public void setReleaseDate(Date newReleaseDate) {
	this.releaseDate = newReleaseDate;
}
public void setDescription(String newDescription) {
	this.description = newDescription;
}
public void setReceiveDate(Date newReceiveDate) {
	this.receiveDate = newReceiveDate;
}
public void setReceiveDate(String newStrStatus) {
	this.status = Status.valueOf(newStrStatus);
}