export interface Star {
  name: string
  href: string
  description: string
}

export interface StarWithCategory {
  code: string
  name: string
  stars: Star[]
}
