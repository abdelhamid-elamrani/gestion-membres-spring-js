resource "aws_key_pair" "gest-mm-key" {
  key_name   = "gest-mm-key"
  public_key = file("C:/Users/USER/.ssh/gest-mm-key.pub")
}
