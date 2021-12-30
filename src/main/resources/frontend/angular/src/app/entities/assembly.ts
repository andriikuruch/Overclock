import {Motherboard} from "./motherboard";
import {CPU} from "./cpu";
import {GPU} from "./gpu";
import {RAM} from "./ram";
import {Comment} from "./comment";

export interface Assembly {
  id: number;
  name: string;
  motherboard: Motherboard
  cpu: CPU;
  gpu: GPU;
  ram: RAM;
  comments: Comment[];
  author: number;
  overclock: number;
  score: number;
}
