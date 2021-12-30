export interface CPU {
  id?: number;
  name?: string;
  socket?: string;
  manufacturer?: string;
  generation?: string;
  family?: string;
  voltage?: number;
  frequency?: number;
  threadsNumber?: number;
  coresNumber?: number;
}
